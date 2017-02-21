package com.fh.controller.app;

import io.swagger.annotations.Api;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Logger;
import com.fh.util.security.AESCoder;
@Controller 
@RequestMapping(value="/apptest")
@Api(hidden=true)
public class TestController extends BaseController{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
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
	
	
	//@Test
	public void test1(){
		
		Token token=new Token();
		token.setAccount("c70854b630ba47718b1dd109b4f77836");
		token.setExpDate(DateUtil.fomatDate("2017-03-05"));
		token.setAccounttType("2");
		token.setLogDate(DateUtil.fomatTime("2017-02-19 17:53:06"));
		String appt=AESCoder.aesCbcEncrypt(JSON.toJSONString(token), Const.APP_TOKEN_KEY);
		System.out.println(appt);
	}
	@Test
	public void testdoc(){
		
		Token token=new Token();
		token.setAccount("bfb0d058b38147828db4e4d4cd1e6db2");
		token.setInfoId("12dcdc98b32e4c2da6350eb89ed972c6");
		token.setExpDate(DateUtil.fomatDate("2017-03-05"));
		token.setAccounttType("1");
		token.setLogDate(DateUtil.fomatTime("2017-02-21 20:39:36"));
		String appt=AESCoder.aesCbcEncrypt(JSON.toJSONString(token), Const.APP_TOKEN_KEY);
		System.out.println(appt);
	}
	   
	   //@Test
	   public void testxx() throws Exception{
		  // toSmaillImg("d:/1.jpg","d:/11.jpg"); 
//		   File destinationDir = new File("D:/");
//
//		   Thumbnails.of("D:/1.jpg")
//		           .size(200, 200)
//		           .toFiles(destinationDir, Rename.PREFIX_DOT_THUMBNAIL);
		   String MASTER_SECRET="7a9b9828fa7c549646d65d0e";
		   String APP_KEY="40d386f36aa1016831d1a054";
		   
		   JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

		    // For push, all you need do is to build PushPayload object.
		    PushPayload payload = PushPayload.alertAll("123123123");

		    try {
		        PushResult result = jpushClient.sendPush(payload);
		        logger.info("Got result - " + result);

		    } catch (APIConnectionException e) {
		        // Connection error, should retry later
		    	logger.error("Connection error, should retry later", e);

		    } catch (APIRequestException e) {
		        // Should review the error, and fix the request
		    	logger.error("Should review the error, and fix the request", e);
		    	logger.error("HTTP Status: " + e.getStatus());
		    	logger.error("Error Code: " + e.getErrorCode());
		    	logger.error("Error Message: " + e.getErrorMessage());
		    }
		   
	   }
}
