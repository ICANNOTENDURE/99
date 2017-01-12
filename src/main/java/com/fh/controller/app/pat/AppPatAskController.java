package com.fh.controller.app.pat;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.SmsUtil;
import com.fh.util.Tools;

@Controller
@RequestMapping(value="/apppatask")
@Api(value = "病人提问", tags = "病人提问") 
public class AppPatAskController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 插入病人主问题
	 * @return
	 */
	@ApiOperation(notes = "插入病人主问题",  value = "插入病人主问题")
	@RequestMapping(value="/ ",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> getVerification(@RequestParam String account){
		
		JsonResult<Object> result=new JsonResult<Object>();
		if(StringUtils.isBlank(account)){
			return new JsonResult<Object>(2,"手机号码不能为空"); 
		}
		if(!Tools.checkMobileNumber(account)){
			return new JsonResult<Object>(3,"请输入正确的手机号码"); 
		}
		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			int code=Tools.getRandomNum();
			addCookie(Const.COOKIE_Verification_Code, String.valueOf(code), 60);
			SmsUtil.sendSmsAli(account, String.valueOf(code));
		}else{
			result.setCode(2);
			result.setMessage("验证码已发");
		}
		return result;
	}
	
	
}
