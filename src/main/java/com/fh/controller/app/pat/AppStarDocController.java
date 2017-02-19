package com.fh.controller.app.pat;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.system.pat.StarDoc;
import com.fh.entity.vo.doc.DocInfoDetailVO;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.StarDocService;

@Controller
@RequestMapping(value="/appstardoc")
@Api(value = "病人关注医生", tags = "病人关注医生") 
public class AppStarDocController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private StarDocService starDocService;
	
	/**
	 * 病人关注医生
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "病人关注医生",  value = "病人关注医生")
	@RequestMapping(value="/star",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> star(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "医生id",name="docId") @RequestParam String docId
		) throws Exception{
		StarDoc starDoc=new StarDoc();
		starDoc.setDocinfoId(docId);
		starDoc.setPatId(this.getAppUserId());
		commonService.saveOrUpdate(starDoc);
		return new JsonResult<Object>();
	}
	/**
	 * 取消关注医生
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "取消关注医生",  value = "取消关注医生")
	@RequestMapping(value="/starOff",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> starOff(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "医生id",name="docId") @RequestParam String docId
		) throws Exception{
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("docinfo_Id", docId);
		map.put("Pat_id", this.getAppUserId());
		List<StarDoc> starDocs=commonService.selectByEqCon(StarDoc.class,map);
		if(starDocs.size()==1){
			commonService.deleteByPrimaryKey(StarDoc.class, starDocs.get(0).getStarId());
		}
		return new JsonResult<>();
	}
	
	/**
	 * 查询关注医生
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "查询关注医生",  value = "查询关注医生")
	@RequestMapping(value="/listStarDoc",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocInfoDetailVO> listStarDoc(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "一页的显示条数,传空默认为10",name="SHOW_COUNT") @RequestParam Long SHOW_COUNT,
			@ApiParam(value = "当前页数,不传默认为",name="CURRENT_PAGE") @RequestParam Long CURRENT_PAGE
		) throws Exception{
		
		JsonResult<DocInfoDetailVO> jsonResult=new JsonResult<DocInfoDetailVO>();
		Page pg=getAppPage();
		pg.getPd().put("patuserid", this.getAppUserId());
		List<DocInfoDetailVO> detailVOs=starDocService.list(pg);
		jsonResult.setDatas(detailVOs);
		return jsonResult;
	}
	
}
