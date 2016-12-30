package com.fh.controller.app.pat;







import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.util.security.AESCoder;

@Controller
@RequestMapping(value="/apppatuser")
public class PatUserControl extends BaseController{

	@RequestMapping(value="/getToken",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Object> getToken(){
		
		Token token=new Token();
		token.setAccount("13919007855");
		token.setExpDate(new Date());
		String appt=AESCoder.aesCbcEncrypt(JSON.toJSONString(token), "c4ca4238a0b923820dcc509a6f75849b");
		
		String xxString=AESCoder.aesCbcDecrypt(appt, "c4ca4238a0b923820dcc509a6f75849b");
		Token token2=JSON.parseObject(xxString, Token.class);
		return new JsonResult<Object>(0,token2.getAccount());
	}
	
	@AppToken
	@RequestMapping(value="/checkToken",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Object> checkToken(String APP_TOKEN,String APP_USER_CODE,String APP_USER_TYPE){
		
		Token token=new Token();
		token.setAccount("13919007855");
	
		return new JsonResult<Object>(0,JSON.toJSONString(token));
	}
}
