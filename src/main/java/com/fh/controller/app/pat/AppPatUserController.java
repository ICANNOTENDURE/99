package com.fh.controller.app.pat;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.PatUser;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.system.pat.PatFamily;
import com.fh.entity.vo.pat.LoginInfoVO;
import com.fh.entity.vo.token.Token;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.SmsUtil;
import com.fh.util.StringUtil;
import com.fh.util.TokenUtil;
import com.fh.util.Tools;
import com.fh.util.enums.UserType;
import com.fh.util.security.AESCoder;

@Controller
@RequestMapping(value="/apppatuser")
@Api(value = "病人用户模块", tags = "病人用户模块") 
public class AppPatUserController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 获取短信验证码
	 * @return
	 */
	@ApiOperation(notes = "获取短信验证码",  value = "获取短信验证码")
	@RequestMapping(value="/getVerification",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> getVerification(@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account){
		
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
	
	/**
	 * 手机验证码登陆
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "短信验证码登陆",  value = "短信验证码登陆")
	@RequestMapping(value="/checkVerification",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkVerification(
			@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account,
			@ApiParam(value = "验证",name="verifyCode", required = true)  @RequestParam String verifyCode) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(verifyCode)){
			return new JsonResult<>(2, "账号或者验证码不能为空");
		}
		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(3, "无效验证码,请重新获取验证码");
		}
		if(!verifyCode.equals(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(4, "验证码错误");
		}
		
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("user_Account", account);
		parMap.put("status", "Y");
		List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
		if(patUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		patUsers.get(0).setUserLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(patUsers.get(0));
		Token token=new Token();
		token.setAccounttType(UserType.PAT.getType());
		token.setAccount(patUsers.get(0).getUserId());
		token.setLogDate(patUsers.get(0).getUserLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,JSON.toJSONString(tk));
	}
	
	/**
	 * 手机密码登陆
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "账号密码验证码登陆",  value = "账号密码验证码登陆")
	@RequestMapping(value="/checkByPwd",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkByPwd(
			@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account,
			@ApiParam(value = "密码",name="pwd", required = true)	@RequestParam String pwd) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(pwd)){
			return new JsonResult<>(2, "账号或者密码不能为空");
		}
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("user_Account", account);
		parMap.put("user_Password",MD5.md5(pwd));
		parMap.put("status", "Y");
		List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
		if(patUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		patUsers.get(0).setUserLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(patUsers.get(0));
		Token token=new Token();
		token.setAccounttType(UserType.PAT.getType());
		token.setAccount(patUsers.get(0).getUserId());
		token.setLogDate(patUsers.get(0).getUserLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,tk);
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "病人注册账号",  value = "病人注册账号")
	@RequestMapping(value="/addPatUser",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> addPatUser(
			@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account,
			@ApiParam(value = "密码",name="pwd", required = true)	@RequestParam String pwd,
			@ApiParam(value = "验证码",name="verifyCode", required = true)	@RequestParam String verifyCode) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(pwd)||StringUtils.isBlank(verifyCode)){
			return new JsonResult<>(2, "账号或者密码或验证码不能为空");
		}
		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(3, "无效验证码,请重新获取验证码");
		}
		if(!verifyCode.equals(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(4, "验证码错误");
		}
		delCookieValueByName(Const.COOKIE_Verification_Code);
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("user_Account", account);
		List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
		if(patUsers.size()>0){
			return new JsonResult<>(5, "账号已注册");
		}
		PatUser patUser=new PatUser();
		patUser.setStatus("Y");
		patUser.setUserAccount(account);
		patUser.setUserPassword(MD5.md5(pwd));
		patUser.setUserLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(patUser);
		Token token=new Token();
		token.setAccounttType(UserType.PAT.getType());
		token.setAccount(patUser.getUserId());
		token.setLogDate(patUser.getUserLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,tk);
	}
	/**
	 * 找回丢失账号
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "找回丢失账号",  value = "找回丢失账号")
	@RequestMapping(value="/getLostPatUser",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> getLostPatUser(
			@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account,
			@ApiParam(value = "密码",name="pwd", required = true)	@RequestParam String pwd,
			@ApiParam(value = "验证码",name="verifyCode", required = true)	@RequestParam String verifyCode) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(pwd)||StringUtils.isBlank(verifyCode)){
			return new JsonResult<>(2, "账号或者密码或验证码不能为空");
		}
		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(3, "无效验证码,请重新获取验证码");
		}
		if(!verifyCode.equals(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(4, "验证码错误");
		}
		delCookieValueByName(Const.COOKIE_Verification_Code);
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("user_Account", account);
		List<PatUser> patUsers = commonService.selectByEqCon(PatUser.class, parMap);
		if(patUsers.size()==0){
			return new JsonResult<>(5, "账号错误");
		}
		if(!"Y".equals(patUsers.get(0).getStatus())){
			return new JsonResult<>(6, "账号已被冻结");
		}
		patUsers.get(0).setUserPassword(MD5.md5(pwd));
		patUsers.get(0).setUserLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(patUsers.get(0));
		return new JsonResult<Object>();
	}
	/**
	 * 保存用户家属信息
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "新增用户家属信息",  value = "新增用户家属信息")
	@RequestMapping(value="/addPatFamily",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> addPatFamily(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "家属记录id",name="famId") @RequestParam String famId,
			@ApiParam(value = "家属姓名",name="famName",example="张三丰") @RequestParam String famName,
			@ApiParam(value = "性别",name="famSex",example="男") @RequestParam String famSex,
			@ApiParam(value = "生日",name="famBrith",example="2016-01-01") @RequestParam Date famBrith,
			@ApiParam(value = "婚姻状态",name="famMarry",example="字典表 婚姻id") @RequestParam String famMarry,
			@ApiParam(value = "是否默认",name="famFlag",example="Y") @RequestParam String famFlag) throws Exception{
		
		
		PatFamily patFamily=new PatFamily();
		patFamily.setParentId(session.getAttribute("APP_SESSION_ID").toString());
		patFamily.setFamId(StringUtil.trim(famId));
		patFamily.setFamName(famName);
		patFamily.setFamBrith(famBrith);
		patFamily.setFamSex(famSex);
		patFamily.setFamFlag(famFlag);
		patFamily.setFamMarry(famMarry);
		commonService.saveOrUpdate(patFamily);
		return new JsonResult<Object>(0,"success");
	}
	
	/**
	 * 查询患者家属列表
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "查询患者家属列表",  value = "查询患者家属列表")
	@RequestMapping(value="/listPatFamily",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<PatFamily> listPatFamily(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN) throws Exception{
		
		JsonResult<PatFamily> jsonResult=new JsonResult<PatFamily>();
		Map<String, Object> conMapping=new HashMap<String, Object>();
		conMapping.put("parent_Id", session.getAttribute("APP_SESSION_ID").toString());
		List<PatFamily> families=commonService.selectByEqCon(PatFamily.class, conMapping);
		jsonResult.setDatas(families);
		return jsonResult;
	}
	/**
	 * 删除家属用户
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "删除家属用户",  value = "删除家属用户")
	@RequestMapping(value="/deletePatFamily",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> deletePatFamily(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "id",name="id") @RequestParam String id) throws Exception{
		
		commonService.deleteByPrimaryKey(PatFamily.class, id);
		return new JsonResult<Object>();
	}
	
	@AppToken
	@ApiOperation(notes = "获取登录信息",  value = "获取登录信息")
	@RequestMapping(value="/getLoginInfo",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<LoginInfoVO> getLoginInfo(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN
			) throws Exception{
		
		JsonResult<LoginInfoVO> result=new JsonResult<LoginInfoVO>();
		LoginInfoVO infoVO=new LoginInfoVO();
		result.getDatas().add(infoVO);
		if(UserType.DOC.getType().equals(this.getLoginType())){
			Map<String, Object> conMapping=new HashMap<String, Object>();
			DocUser docUser=commonService.selectByPrimaryKey(DocUser.class, this.getAppUserId());
			conMapping.put("doc_Id", docUser.getDocId());
			List<DocInfo> docInfos=commonService.selectByEqCon(DocInfo.class, conMapping);
			infoVO.setAccount(docUser.getDocAccount());
			if(docInfos.size()==1){
				infoVO.setImg(docInfos.get(0).getDocPic());
				infoVO.setAmt(docInfos.get(0).getAmt());
			}

		}
		if(UserType.PAT.getType().equals(this.getLoginType())){
			PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, this.getAppUserId());
			infoVO.setAccount(patUser.getUserAccount());
			infoVO.setImg(patUser.getUserImg());
			infoVO.setAmt(patUser.getAmt());
		}
		return result;
	}
	
	@AppToken
	@ApiOperation(notes = "修改密码",  value = "修改密码")
	@RequestMapping(value="/updatePassword",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> updatePassword(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "code",name="验证码") @RequestParam String code,
			@ApiParam(value = "password",name="新密码") @RequestParam String password
			) throws Exception{
		

		if(!code.equals(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(4, "验证码错误");
		}
		delCookieValueByName(Const.COOKIE_Verification_Code);		
		PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, getAppUserId());
		patUser.setUserPassword(MD5.md5(password));
		patUser.setUserLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(patUser);
		
		return new JsonResult<Object>(0,TokenUtil.patToken(patUser));
	}
	
	/**
	 * 根据token获取验证码
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "根据token获取验证码",  value = "根据token获取验证码")
	@RequestMapping(value="/getVerifByToken",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> getVerifByToken(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN
		) throws Exception{
		
		JsonResult<Object> result=new JsonResult<Object>();
		String account="";

		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			if(UserType.PAT.getType().equals(this.getLoginType())){
				PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, this.getAppUserId());
				account=patUser.getUserAccount();
			}else{
				DocUser docUser=commonService.selectByPrimaryKey(DocUser.class, this.getAppUserId());
				account=docUser.getDocAccount();
			}
			int code=Tools.getRandomNum();
			addCookie(Const.COOKIE_Verification_Code, String.valueOf(code), 60);
			SmsUtil.sendSmsAli(account, String.valueOf(code));
		}else{
			result.setCode(2);
			result.setMessage("验证码已发");
		}
		return result;
	}
	
	/**
	 * 根据登录用户头像
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "根据登录用户头像",  value = "根据登录用户头像")
	@RequestMapping(value="/getVerifByToken",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> updatePic(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "pic",name="pic") @RequestParam String pic
			) throws Exception{
		
		if(UserType.PAT.getType().equals(this.getLoginType())){
			PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, this.getAppUserId());
			patUser.setUserImg(pic);
			commonService.saveOrUpdate(patUser);
		}else{
			DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, this.getLoginInfoId());
			docInfo.setDocPic(pic);
			commonService.saveOrUpdate(docInfo);
		}
		return new JsonResult<Object>();
	}
}
