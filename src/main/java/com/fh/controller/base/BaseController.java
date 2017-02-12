package com.fh.controller.base;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.entity.Page;
import com.fh.entity.vo.token.Token;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;
import com.fh.util.security.AESCoder;


public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	 protected HttpServletRequest request;
	 protected HttpServletResponse response;
	 protected HttpSession session;

	  

	   @ModelAttribute
	   public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
	
	       this.request = request;
	       this.response = response;
	       this.session = request.getSession();
	
	   }
	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	public Page getAppPage(){
		Page page=new Page();
		page.setPd(getPageData());
		page.setShowCount(StringUtils.isBlank(getPar("SHOW_COUNT"))?10:Integer.valueOf(getPar("SHOW_COUNT")));
		page.setCurrentPage(StringUtils.isBlank(getPar("CURRENT_PAGE"))?1:Integer.valueOf(getPar("CURRENT_PAGE")));
		return page;
	}
	
	/**得到ModelAndView
	 * @return
	 */
	public ModelAndView getModelAndView(){
		ModelAndView mv=new ModelAndView().addObject("msg", "success");
		if(Jurisdiction.getSession().getAttribute(Const.SESSION_USERNAME)!=null){
			mv.addObject("QX",Jurisdiction.getHC());
		}
		mv.addObject("SYSNAME", "百姓药房");
		return mv;
	}

	/**得到request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UuidUtil.get32UUID();
	}
	
	/**得到分页列表的信息
	 * @return
	 */
	public Page getPage(){
		return new Page();
	}
	/**
	 * 
	* @Title: getPar 
	* @Description: TODO(获取前台参数) 
	* @param @param parName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author zhouxin   
	* @date 2016年12月28日 上午10:32:02
	 */
	public String getPar(String parName){
		return getRequest().getParameter(parName)==null?"":getRequest().getParameter(parName).toString();
	}
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}

	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    }
	
	
	/**
     * 添加cookie
     * 
     * @param response
     * @param name
     * @param value
     * @param maxAge 有效时间
     */
    public  void addCookie(String name, String value, int maxAge) {
    	 if (null == name) {  
             return;  
         }  
         if (null == value) {  
             value = "";  
         }  
         Cookie cookie = new Cookie(name, value);  
         cookie.setPath("/");  
         if (maxAge != 0) {  
             cookie.setMaxAge(maxAge);  
         } else {  
             cookie.setMaxAge(Const.COOKIE_Max_Age);  
         }  
         response.addCookie(cookie); 
    }


    /**
     * 通过cookie name 获取 cookie value
     * 
     * @param request
     * @param name
     * @return
     */
    public  String getCookieValueByName(String name) {
    	Cookie[] cookies = request.getCookies();  
        if (null == cookies || null == name || name.length() == 0) {  
            return null;  
        }  
        String cookie = null;  
        for (Cookie c : cookies) {  
            if (name.equals(c.getName())) {  
                cookie = c.getValue();  
                break;  
            }  
        }  
        return cookie; 
    }
    /**
     * 通过cookie name 删除 cookie value
     * 
     * @param request
     * @param name
     * @return
     */
    public  String delCookieValueByName(String name) {
    	Cookie[] cookies = request.getCookies();  
        if (null == cookies || null == name || name.length() == 0) {  
            return null;  
        }  
        String cookie = null;  
        for (Cookie c : cookies) {  
            if (name.equals(c.getName())) {  
            	c.setValue(null);
                c.setMaxAge(0);// 立即销毁cookie
                c.setPath("/");
                response.addCookie(c);
            }  
        }  
        return cookie; 
    }
    /**
     * 获取登陆用户id
     * @return
     */
    public String getAppUserId(){
    	String APP_TOKEN=getPar("APP_TOKEN");
    	if(StringUtils.isNotBlank(APP_TOKEN)){
			String str=AESCoder.aesCbcDecrypt(APP_TOKEN, Const.APP_TOKEN_KEY);
			Token token=JSON.parseObject(str, Token.class);
			return token.getAccount();
    	}
    	return "";
    }
}
