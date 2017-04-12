package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
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
import com.fh.entity.Page;
import com.fh.entity.app.AppDisease;
import com.fh.entity.app.AppHop;
import com.fh.entity.app.AppLoc;
import com.fh.entity.system.Dictionaries;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocSpecialDisease;
import com.fh.entity.vo.Select;
import com.fh.entity.vo.doc.DocInfoDetailVO;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.service.system.doc.impl.DocService;
import com.fh.util.Constants;
import com.fh.util.PageData;
import com.fh.util.enums.AuditDocStatusEnum;


@Controller 
@RequestMapping(value="/appdoc")
@Api(value = "医生列表", tags = "医生列表") 
public class AppdocController extends BaseController{

	
	@Autowired
	private DocService docService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 返回科室信息
	 * @return
	 */
	@ApiOperation(notes = "医生列表",  value = "医生列表")
	@RequestMapping(value="/getAppDoc",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocServiceVO> getAppDoc(
			@ApiParam(value = "DOC_HOPID",name="医院的id") @RequestParam String DOC_HOPID,
			@ApiParam(value = "DOC_LOCID",name="科室id") @RequestParam String DOC_LOCID,
			@ApiParam(value = "DOC_SER",name="医生服务") @RequestParam String DOC_SER,
			@ApiParam(value = "KEYWORD",name="关键字") @RequestParam String KEYWORD,
			@ApiParam(value = "SORT_ORDER",name="排序字段") @RequestParam String SORT_ORDER,
			@ApiParam(value = "SORT",name="排序，升序还是倒叙") @RequestParam String SORT,
			@ApiParam(value = "SHOW_COUNT",name="一页的显示条数,传空默认为10") @RequestParam String SHOW_COUNT,
			@ApiParam(value = "CURRENT_PAGE",name="当前页数,不传默认为1") @RequestParam String CURRENT_PAGE){
		
		JsonResult<DocServiceVO> jsonResult=new JsonResult<DocServiceVO>();
		try {
			Page pg=this.getAppPage();
			if(StringUtils.isNotBlank(KEYWORD)){
				KEYWORD = new String(KEYWORD.getBytes("ISO-8859-1"),"UTF-8");
				pg.getPd().put("KEYWORD", KEYWORD);
			}
			pg.getPd().put("status", AuditDocStatusEnum.AUDIT_PASS.getCode());
			pg.getPd().put("ORDER", "t2.doc_Seq");
			if(StringUtils.isNotBlank(SORT)&&StringUtils.isNotBlank(SORT_ORDER)){
				if("1".equals(SORT_ORDER)){
					pg.getPd().put("ORDER","t2.doc_Server_Num");
				}
				if("2".equals(SORT_ORDER)){
					pg.getPd().put("ORDER", "t2.doc_Reply_Num");
				}
			}
			jsonResult.setDatas(docService.listPage(pg));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 医生服务类型
	 * @return
	 */
	@ApiOperation(notes = "医生服务类型",  value = "医生服务类型")
	@RequestMapping(value="/getDocService",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Select> getDocService(){
		
		JsonResult<Select> jsonResult=new JsonResult<Select>();
		List<Select> list=new ArrayList<Select>();
		jsonResult.setDatas(list);
		try {
			
			List<Dictionaries> dictionaries=dictionariesService.listSubDictByParentId(Constants.DIC_DOC_SERVICE);
			for(Dictionaries dictionaries2:dictionaries){
				list.add(new Select(dictionaries2.getDICTIONARIES_ID(), dictionaries2.getNAME()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 医生服务类型
	 * @return
	 */
	@ApiOperation(notes = "医生职称",  value = "医生职称")
	@RequestMapping(value="/getDocTitle",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Select> getDocTitle(){
		
		JsonResult<Select> jsonResult=new JsonResult<Select>();
		List<Select> list=new ArrayList<Select>();
		jsonResult.setDatas(list);
		try {
			
			List<Dictionaries> dictionaries=dictionariesService.listSubDictByParentId(Constants.DIC_DOC_TITLE);
			for(Dictionaries dictionaries2:dictionaries){
				list.add(new Select(dictionaries2.getDICTIONARIES_ID(), dictionaries2.getNAME()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 医生明细信息
	 * @return
	 */
	@ApiOperation(notes = "医生明细信息",  value = "医生明细信息")
	@RequestMapping(value="/getDocInfo",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocInfoDetailVO> getDocInfo(@ApiParam(value = "医生id",name="docId", required = true) @RequestParam String docId){
		
		JsonResult<DocInfoDetailVO> jsonResult=new JsonResult<DocInfoDetailVO>();
		List<DocInfoDetailVO> list=new ArrayList<DocInfoDetailVO>();
		jsonResult.setDatas(list);
		try {
			DocInfoDetailVO detailVO=new DocInfoDetailVO();
			DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, docId);
			
			detailVO.setDocImg(docInfo.getDocPic());
			detailVO.setDocIntroduce(docInfo.getDocIntroduce());
			detailVO.setDocName(docInfo.getDocName());
			detailVO.setDocReplyNum(docInfo.getDocReplyNum());
			detailVO.setDocServerNum(docInfo.getDocServerNum());
			if(StringUtils.isNotBlank(docInfo.getDocLocid())){
				detailVO.setLocName(commonService.selectByPrimaryKey(AppLoc.class, docInfo.getDocLocid()).getLocName());
			}
			if(StringUtils.isNotBlank(docInfo.getDocHopid())){
				detailVO.setHopName(commonService.selectByPrimaryKey(AppHop.class, docInfo.getDocHopid()).getHopName());
			}
			if(StringUtils.isNotBlank(docInfo.getDocTitle())){
				PageData pd=new PageData();
				pd.put("DICTIONARIES_ID", docInfo.getDocTitle());
				detailVO.setDocTitle(dictionariesService.findById(pd).getString("NAME"));
			}
			Map<String,Object> conMapping=new HashMap<String, Object>();
			conMapping.put("docinfo_Id", docId);
			List<DocSpecialDisease> diseases=commonService.selectByEqCon(DocSpecialDisease.class, conMapping);
			if(!diseases.isEmpty()){
				StringBuffer sb=new StringBuffer();
				for(DocSpecialDisease disease:diseases){
					conMapping.clear();
					conMapping.put("disease_Id", disease.getDiseaseId());
					List<AppDisease> appDisease=commonService.selectByEqCon(AppDisease.class, conMapping);
					if(!appDisease.isEmpty()){
						sb.append(appDisease.get(0).getDiseaseName()+",");
					}
				}
				detailVO.setDocDisease(sb.toString());
			}
			conMapping.clear();
			conMapping.put("docinfo_Id", docId);
			List<com.fh.entity.system.doc.DocService> docServices=commonService.selectByEqCon(com.fh.entity.system.doc.DocService.class, conMapping);
			if(!docServices.isEmpty()){
				for(com.fh.entity.system.doc.DocService docService:docServices){
					PageData pd=new PageData();
					pd.put("DICTIONARIES_ID", docService.getServiceType());
					docService.setServiceTypeName(dictionariesService.findById(pd).getString("NAME"));
				}
				detailVO.setDocServices(docServices);
			}
			
			list.add(detailVO);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}

}
