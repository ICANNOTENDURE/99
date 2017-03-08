package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocInfoReq;
import com.fh.entity.system.doc.DocService;
import com.fh.entity.system.doc.DocServiceReq;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.doc.DocInfoStatusVO;
import com.fh.entity.vo.doc.DocServiceStatusVO;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.fh.util.TokenUtil;
import com.fh.util.enums.AuditDocStatusEnum;


@Controller 
@RequestMapping(value="/appdocuser")
@Api(value = "医生用户登录操作", tags = "医生用户登录操作") 
public class AppdocUserController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DictionariesService dictionariesService;
	
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
		parMap.clear();
		parMap.put("doc_Id", docUsers.get(0).getDocId());
		List<DocInfo> docInfos = commonService.selectByEqCon(DocInfo.class, parMap);
		if(docInfos.size()>0){
			return new JsonResult<Object>(0,TokenUtil.docToken(docUsers.get(0),docInfos.get(0).getInfoId()));
		}else{
			return new JsonResult<Object>(0,TokenUtil.docToken(docUsers.get(0),""));
		}
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
		
		parMap.clear();
		parMap.put("doc_Id", docUsers.get(0).getDocId());
		List<DocInfo> docInfos = commonService.selectByEqCon(DocInfo.class, parMap);
		if(docInfos.size()>0){
			return new JsonResult<Object>(0,TokenUtil.docToken(docUsers.get(0),docInfos.get(0).getInfoId()));
		}else{
			return new JsonResult<Object>(0,TokenUtil.docToken(docUsers.get(0),""));
		}
		
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
		return new JsonResult<Object>(0,TokenUtil.docToken(docUser,""));
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
	
	

	@AppToken
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
		
		DocInfo docInfo=new DocInfo();
		docInfo.setInfoId(this.getLoginInfoId());
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
		docInfo.setAuditFlag(AuditDocStatusEnum.AUDIT_ING.getCode());
		commonService.saveOrUpdate(docInfo);
		DocInfoReq docInfoReq=new DocInfoReq();
		docInfoReq.setContent("医师申请");
		docInfoReq.setCreateDate(new Date());
		docInfoReq.setStatus(AuditDocStatusEnum.AUDIT_ING.getCode());
		docInfoReq.setReqInfoId(docInfo.getInfoId());
		commonService.saveOrUpdate(docInfoReq);
		return new JsonResult<>();
	}	
	

	@AppToken
	@ApiOperation(notes = "医生申请服务",  value = "医生申请服务")
	@RequestMapping(value="/saveDocService",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> saveDocService(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "服务类型id",name="serviceTypeId") @RequestParam String serviceTypeId,
			@ApiParam(value = "服务价格",name="price")	@RequestParam BigDecimal price) throws Exception{
		JsonResult<Object> jsonResult=new JsonResult<Object>();
		if(StringUtils.isBlank(super.getLoginInfoId())){
			jsonResult.setCode(2);
			jsonResult.setMessage("请先认证医生资格");
			return jsonResult;
		}
		DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, getLoginInfoId());
		if(!AuditDocStatusEnum.AUDIT_PASS.getCode().equals(docInfo.getAuditFlag())){
			jsonResult.setCode(2);
			jsonResult.setMessage("请先认证医生资格");
			return jsonResult;
		}
		DocService docService=new DocService();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("service_Type", serviceTypeId);
		map.put("docinfo_Id", docInfo.getInfoId());
		List<DocService> docServices=commonService.selectByEqCon(DocService.class, map);
		if(docServices.size()>0){
			if(AuditDocStatusEnum.AUDIT_PASS.getCode().equals(docServices.get(0).getAuditFlag())){
				jsonResult.setCode(3);
				jsonResult.setMessage("已经申请成功");
				return jsonResult;
			}
			docService.setServiceId(docServices.get(0).getServiceId());
		}
		docService.setServicePrice(price);
		docService.setDocinfoId(docInfo.getInfoId());
		docService.setAuditFlag(AuditDocStatusEnum.AUDIT_ING.getCode());
		docService.setServiceType(serviceTypeId);
		commonService.saveOrUpdate(docService);
		DocServiceReq docServiceReq=new DocServiceReq();
		docServiceReq.setContent("医师申请");
		docServiceReq.setCreateDate(new Date());
		docServiceReq.setStatus(AuditDocStatusEnum.AUDIT_ING.getCode());
		docServiceReq.setServiceId(docService.getServiceId());
		commonService.saveOrUpdate(docServiceReq);
		return jsonResult;
	}
	
	@AppToken
	@ApiOperation(notes = "医生，服务认证状态",  value = "医生，服务认证状态")
	@RequestMapping(value="/getAuditInfo",method = RequestMethod.POST)
	@ResponseBody
	public DocInfoStatusVO getAuditInfo(
			@ApiParam(value = "APP_TOKEN",name="APP_TOKEN") @RequestParam String APP_TOKEN) throws Exception{
		DocInfoStatusVO docInfoStatusVO=new DocInfoStatusVO();
		
		if(StringUtils.isBlank(super.getLoginInfoId())){
			docInfoStatusVO.setDocQualifyStatus(AuditDocStatusEnum.TO_AUDIT.getCode());
		}else{
			DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, getLoginInfoId());
			docInfoStatusVO.setDocQualifyStatus(docInfo.getAuditFlag());
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("docinfo_Id", docInfo.getInfoId());
			List<DocService> docServices=commonService.selectByEqCon(DocService.class, map);
			List<DocServiceStatusVO> docServiceStatusVOs=new ArrayList<DocServiceStatusVO>();
			PageData pd=new PageData();
			for(DocService docService:docServices){
				
				DocServiceStatusVO serviceVO=new DocServiceStatusVO();
				serviceVO.setServiceId(docService.getServiceId());
				serviceVO.setServicePrice(docService.getServicePrice());
				serviceVO.setServiceStatus(docService.getAuditFlag());
				pd.put("DICTIONARIES_ID", docService.getServiceType());
				serviceVO.setServiceName(dictionariesService.findById(pd).getString("NAME"));
				docServiceStatusVOs.add(serviceVO);
			}
			docInfoStatusVO.setDocServiceStatusVOs(docServiceStatusVOs);
		}
		return docInfoStatusVO;
	}
}
