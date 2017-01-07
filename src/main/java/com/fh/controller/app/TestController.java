package com.fh.controller.app;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.security.AESCoder;
@Controller 
@RequestMapping(value="/apptest")
public class TestController extends BaseController{
	@RequestMapping(value="/getToken",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Object> getToken(){
		
		Token token=new Token();
		token.setAccount("13919007855");
		token.setExpDate(DateUtil.fomatDate(DateUtil.getDay()));
		token.setAccounttType("1");
		token.setLogDate(DateUtil.fomatDate(DateUtil.getDay()));
		String appt=AESCoder.aesCbcEncrypt(JSON.toJSONString(token), Const.APP_TOKEN_KEY);
		System.out.println(appt);
		String xxString=AESCoder.aesCbcDecrypt(appt,Const.APP_TOKEN_KEY);
		Token token2=JSON.parseObject(xxString, Token.class);
		return new JsonResult<Object>(0,appt);
	}
	
	@AppToken
	@RequestMapping(value="/checkToken",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkToken(String APP_TOKEN,String APP_USER_CODE,String APP_USER_TYPE){
		
		Token token=new Token();
		token.setAccount("13919007855");
	
		return new JsonResult<Object>(0,JSON.toJSONString(token));
	}
	
	@RequestMapping(value="/test")
	public ModelAndView list(Page page){
		
			//15920908325
		//15095394330
		//SmsUtil.sendSmsAli("15095394330","梁嘉权吃翔");

		
		ModelAndView mv = getModelAndView();
		PageData pd=getPageData();
		page.setPd(pd);
		try{
			mv.setViewName("test");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

}
