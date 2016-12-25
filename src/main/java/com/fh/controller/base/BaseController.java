package com.fh.controller.base;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.Page;
import com.fh.plugin.GeneralQueryParam;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.UuidUtil;
import com.fh.util.security.AESCoder;


public class BaseController {
	
	protected Logger logger = Logger.getLogger(this.getClass());


	
	/** new PageData对象
	 * @return
	 */
	public PageData getPageData(){
		return new PageData(this.getRequest());
	}
	
	public GeneralQueryParam getQueryParam(Page page){
		
		GeneralQueryParam generalQueryParam=new GeneralQueryParam();
		generalQueryParam.setPageNo(page.getCurrentPage()+1);
		generalQueryParam.setPageSize(page.getShowCount());
		return generalQueryParam;
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
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
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
	
	public static void logBefore(Logger logger, String interfaceName){
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}
	
	public static void logAfter(Logger logger){
		logger.info("end");
		logger.info("");
	}
	public  boolean checkToken(){
		String token=getRequest().getParameter("APP_TOKEN")==null?"":getRequest().getParameter("APP_TOKEN").toString();
		if(StringUtils.isBlank(token)){
			return false;
		}
		String userCode=getRequest().getParameter("APP_USER_CODE")==null?"":getRequest().getParameter("APP_USER_CODE").toString();
		if(StringUtils.isBlank(userCode)){
			return false;
		}
		String tmp=AESCoder.aesCbcEncrypt(userCode, Const.APP_TOKEN_KEY).replace("+"," ");
		if(token.equals(tmp)){
			return true;
		}
		return false;
	}
	@InitBinder   
    public void initBinder(WebDataBinder binder) {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
        dateFormat.setLenient(true);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
    } 
}
