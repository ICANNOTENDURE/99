package com.fh.interceptor;

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
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
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
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
						String APP_USER_CODE = request.getParameter("APP_USER_CODE");
						String APP_USER_TYPE = request.getParameter("APP_USER_TYPE");
						if (StringUtils.isBlank(APP_TOKEN) || StringUtils.isBlank(APP_USER_CODE) || StringUtils.isBlank(APP_USER_TYPE)) {
							jsonResult.setCode(10);
							jsonResult.setMessage("APP_TOKEN或APP_USER_CODE或APP_USER_TYPE不能为空");
						}else{
							String pwd = "";
							Map<String, Object> parMap = new HashMap<String, Object>();
							// 医生用户
							if ("1".equals(APP_USER_TYPE)) {
								parMap.put("doc_Account", APP_USER_CODE);
								List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
								if (docUsers.size() > 0) {
									if ("Y".equals(docUsers.get(0).getStatus())) {
										pwd = docUsers.get(0).getDocPassword();
									}
								}
							}
							// 病人用户
							if ("2".equals(APP_USER_TYPE)) {
								parMap.put("user_Account", APP_USER_CODE);
								List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
								if (patUsers.size() > 0) {
									if ("Y".equals(patUsers.get(0).getStatus())) {
										pwd = patUsers.get(0).getUserPassword();
									}
								}
							}
							if (StringUtils.isBlank(pwd)) {
								jsonResult.setCode(11);
								jsonResult.setMessage("用户不存在或者用户状态异常");
							}else{
								String decode = AESCoder.aesCbcEncrypt(APP_USER_CODE, pwd);
								if (!APP_TOKEN.equals(decode)) {
									jsonResult.setCode(12);
									jsonResult.setMessage("APP_TOKEN错误");
								}
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
