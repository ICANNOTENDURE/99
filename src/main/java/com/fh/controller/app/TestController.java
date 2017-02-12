package com.fh.controller.app;

import io.swagger.annotations.Api;

import java.io.File;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import org.junit.Test;
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
import com.fh.util.security.AESCoder;
@Controller 
@RequestMapping(value="/apptest")
@Api(hidden=true)
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
		return new JsonResult<Object>();
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
		
		ModelAndView mv = getModelAndView();

			mv.setViewName("test");

		return mv;
	}
	@RequestMapping(value="/test2")
	public ModelAndView list2(Page page){
		
		ModelAndView mv = getModelAndView();
			mv.setViewName("uploadify");
		return mv;
	}
	
	
	@Test
	public void test(){
		
		Token token=new Token();
		token.setAccount("c70854b630ba47718b1dd109b4f77836");
		token.setExpDate(DateUtil.fomatDate("2017-03-05"));
		token.setAccounttType("2");
		token.setLogDate(DateUtil.fomatTime("2017-02-12 14:57:08"));
		String appt=AESCoder.aesCbcEncrypt(JSON.toJSONString(token), Const.APP_TOKEN_KEY);
		System.out.println(appt);
		//System.out.println((AskStatus.getValueByKey("1")));
		
		System.out.println(AESCoder.aesCbcDecrypt(appt, Const.APP_TOKEN_KEY));
	}

	   
	   //@Test
	   public void testxx() throws Exception{
		  // toSmaillImg("d:/1.jpg","d:/11.jpg"); 
		   File destinationDir = new File("D:/");

		   Thumbnails.of("D:/1.jpg")
		           .size(200, 200)
		           .toFiles(destinationDir, Rename.PREFIX_DOT_THUMBNAIL);
		   
	   }
}
