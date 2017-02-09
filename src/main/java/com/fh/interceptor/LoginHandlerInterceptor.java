package com.fh.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.fh.entity.JsonResult;
import com.fh.entity.system.PatUser;
import com.fh.entity.system.User;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.security.AESCoder;

public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private CommonService commonService;
	
	
	/**
	 * code
	 * 10:APP_TOKEN或APP_USER_CODE或APP_USER_TYPE不能为空
	 * 11：用户不存在或者用户状态异常
	 * 12：APP_TOKEN错误
	 * 13：APP_TOKEN错误
	 * @throws IOException 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException  {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if (path.matches(Const.NO_INTERCEPTOR_PATH)) {
			if (path.matches(Const.APP_PATH)) {
				if (handler instanceof HandlerMethod) {
					HandlerMethod method = (HandlerMethod) handler;
					AppToken appToken = method.getMethodAnnotation(AppToken.class);
					if (appToken != null) {
						JsonResult<Object> jsonResult = new JsonResult<>();
						String APP_TOKEN = request.getParameter("APP_TOKEN");
						if (StringUtils.isBlank(APP_TOKEN)) {
							jsonResult.setCode(10);
							jsonResult.setMessage("APP_TOKEN不能为空");
						}else{
							try {
								String str=AESCoder.aesCbcDecrypt(APP_TOKEN, Const.APP_TOKEN_KEY);
								Token token=JSON.parseObject(str, Token.class);
								if(token.getExpDate().getTime()<=DateUtil.fomatDate(DateUtil.getDay()).getTime()){
									jsonResult.setCode(11);
									jsonResult.setMessage("TOKEN过期,请重新登陆");
								}else{
									Map<String, Object> parMap = new HashMap<String, Object>();
									// 医生用户
									if ("1".equals(token.getAccounttType())) {
										parMap.put("doc_id", token.getAccount());
										parMap.put("status", "Y");
										parMap.put("doc_Logindate", token.getLogDate());
										List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
										if (docUsers.size() == 0) {
											jsonResult.setCode(11);
											jsonResult.setMessage("用户状态异常");
										}else{
											request.getSession().setAttribute("APP_SESSION_ID", docUsers.get(0).getDocId());
										}
									}
									// 病人用户
									if ("2".equals(token.getAccounttType())) {
										parMap.put("user_id", token.getAccount());
										parMap.put("status", "Y");
										parMap.put("user_Logindate", token.getLogDate());
										List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
										if (patUsers.size() == 0) {
											jsonResult.setCode(11);
											jsonResult.setMessage("用户状态异常");
										}else{
											request.getSession().setAttribute("APP_SESSION_ID", patUsers.get(0).getUserId());
										}
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								response.setCharacterEncoding("UTF-8");
								response.setContentType("application/json; charset=utf-8");
								jsonResult.setCode(12);
								jsonResult.setMessage("TOKEN错误");
								response.getWriter().write(JSON.toJSONString(jsonResult));
								return false;
							}
						}
						if (jsonResult.getCode() > 0) {
							response.setCharacterEncoding("UTF-8");
							response.setContentType("application/json; charset=utf-8");
							response.getWriter().write(JSON.toJSONString(jsonResult));
							return false;
						}
					}
				}
			}
			return true;

		} else {
			User user = (User) Jurisdiction.getSession().getAttribute(Const.SESSION_USER);
			if (user != null) {
				path = path.substring(1, path.length());
				boolean b = Jurisdiction.hasJurisdiction(path); // 访问权限校验
				if (!b) {
					response.sendRedirect(request.getContextPath() + Const.LOGIN);
				}
				return b;
			} else {
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Const.LOGIN);
				return false;
			}
		}
	}

}
