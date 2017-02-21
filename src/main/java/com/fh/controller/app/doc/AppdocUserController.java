 package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.token.Token;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.enums.UserType;
import com.fh.util.security.AESCoder;


@Controller 
@RequestMapping(value="/appdocuser")
@Api(value = "医生用户登录操作", tags = "医生用户登录操作") 
public class AppdocUserController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 医生用户手机验证码登陆
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "短信验证码登陆",  value = "短信验证码登陆")
	@RequestMapping(value="/checkVerification",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkVerification(
			@ApiParam(value = "手机号",name="account", required = true) @RequestParam String account,
			@ApiParam(value = "验证码",name="verifyCode", required = true) @RequestParam String verifyCode) throws Exception{
		
		
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
		parMap.put("doc_Account", account);
		parMap.put("status", "Y");
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		docUsers.get(0).setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(docUsers.get(0));
		Token token=new Token();
		token.setAccounttType("1");
		token.setAccount(docUsers.get(0).getDocId());
		token.setLogDate(docUsers.get(0).getDocLogindate());
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
			@ApiParam(value = "手机号",name="account", required = true)	@RequestParam String account,
			@ApiParam(value = "密码",name="pwd", required = true) @RequestParam String pwd) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(pwd)){
			return new JsonResult<>(2, "账号或者密码不能为空");
		}
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("doc_Account", account);
		parMap.put("doc_Password",MD5.md5(pwd));
		parMap.put("status", "Y");
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		docUsers.get(0).setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(docUsers.get(0));
		Token token=new Token();
		token.setAccounttType(UserType.DOC.getType());
		token.setAccount(docUsers.get(0).getDocId());
		token.setLogDate(docUsers.get(0).getDocLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,tk);
	}
	
	
	/**
	 * 增加医生用户
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "增加医生用户",  value = "增加医生用户")
	@RequestMapping(value="/addDocUser",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> addDocUser(
			@ApiParam(value = "手机号",name="account", required = true)	@RequestParam String account,
			@ApiParam(value = "密码",name="pwd", required = true) @RequestParam String pwd,
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
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("doc_Account", account);
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()>0){
			return new JsonResult<>(5, "账号已存在");
		}
		DocUser docUser=new DocUser();
		docUser.setDocAccount(account);
		docUser.setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		docUser.setStatus("Y");
		docUser.setDocPassword(MD5.md5(pwd));
		commonService.saveOrUpdate(docUser);
		Token token=new Token();
		token.setAccounttType(UserType.DOC.getType());
		token.setAccount(docUser.getDocId());
		token.setLogDate(docUser.getDocLogindate());
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
	@RequestMapping(value="/getLostDocUser",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> getLostDocUser(
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
		parMap.put("doc_Account", account);
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()==0){
			return new JsonResult<>(5, "账号错误");
		}
		if(!"Y".equals(docUsers.get(0).getStatus())){
			return new JsonResult<>(6, "账号已被冻结");
		}
		docUsers.get(0).setDocPassword(MD5.md5(pwd));
		docUsers.get(0).setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(docUsers.get(0));
		return new JsonResult<Object>();
	}
	
	
	/**
	 * 医生账户认证
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(notes = "医生账户认证",  value = "医生账户认证")
	@RequestMapping(value="/saveDocInfo",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> saveDocInfo(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "医院id",name="hopId") @RequestParam String hopId,
			@ApiParam(value = "科室id",name="locId")	@RequestParam String locId,
			@ApiParam(value = "身份证",name="idCard")	@RequestParam String idCard,
			@ApiParam(value = "医生职称id",name="titleId")	@RequestParam String titleId,
			@ApiParam(value = "医生照片名称",name="docImg")	@RequestParam String docImg,
			@ApiParam(value = "工牌照片名称",name="workCardImg")	@RequestParam String workCardImg,
			@ApiParam(value = "身份证照片名称",name="idCardImg")	@RequestParam String idCardImg,
			@ApiParam(value = "医师资格证照片名称",name="docQualifyImg")	@RequestParam String docQualifyImg,
			@ApiParam(value = "姓名",name="docName")	@RequestParam String docName) throws Exception{
		
		Map<String, Object> conMapping=new HashMap<String, Object>();
		conMapping.put("doc_Id", this.getAppUserId());
		List<DocInfo>  docInfos=commonService.selectByEqCon(DocInfo.class, conMapping);
		DocInfo docInfo=new DocInfo();
		if(docInfos.size()>0){
			docInfo=docInfos.get(0);
		}
		docInfo.setDocId(this.getAppUserId());
		docInfo.setDocHopid(hopId);
		docInfo.setDocLocid(locId);
		docInfo.setDocPic(docImg);
		docInfo.setDocName(docName);
		docInfo.setDocTitle(titleId);
		docInfo.setDocWorkCardImg(workCardImg);
		docInfo.setDocQualificationImg(docQualifyImg);
		docInfo.setDocIdCard(idCard);
		docInfo.setDocIdCardImg(idCardImg);
		commonService.saveOrUpdate(docInfo);
		return new JsonResult<>();
	}	
	
}
