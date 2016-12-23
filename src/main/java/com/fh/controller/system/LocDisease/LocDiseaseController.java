package com.fh.controller.system.LocDisease;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppDisease;
import com.fh.entity.app.AppLoc;
import com.fh.entity.app.LocDisease;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.LocDiseaseService;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/locdisease")
public class LocDiseaseController extends BaseController{

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private LocDiseaseService locDiseaseService;
	
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		PageData pd=getPageData();
		page.setPd(pd);
		try{
			mv.setViewName("system/locdisease/list");
			mv.addObject("list", locDiseaseService.listPage(page));
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/saveOrUpdate")
	@ResponseBody
	public JsonResult saveOrUpdate(LocDisease locDisease){
		if(StringUtils.isBlank(locDisease.getLocId())||StringUtils.isBlank(locDisease.getDiseaseId())){
			return new JsonResult(1,"入参不能为空");
		}
		try {
			Map<String,Object> con=new HashMap<>();
			con.put("LOC_ID", locDisease.getLocId());
			con.put("DISEASE_ID", locDisease.getDiseaseId());
			List<LocDisease> locDiseases=commonService.selectByEqCon(LocDisease.class, con);
			if(locDiseases.size()>0){
				return new JsonResult(3,"已存在，不能重复添加");
			}
			commonService.saveOrUpdate(locDisease);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(3,e.getMessage());
		}
		return new JsonResult();
	}
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public JsonResult delete(@PathVariable String id) throws Exception{
		
		commonService.deleteByPrimaryKey(LocDisease.class, id);
		return new JsonResult();
	}
	
	/**批量删除
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll/{ids}")
	@ResponseBody
	public JsonResult deleteAll(@PathVariable String ids){
		
		String[] arr=ids.split(",");
		for(int i=0;i<arr.length;i++){
			commonService.deleteByPrimaryKey(LocDisease.class, arr[i]);
		}	
		return new JsonResult();
	}
	
	/**进入上传excel界面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception{
		ModelAndView mv =getModelAndView();
		mv.addObject("path","locdisease/readExcel.do");
		mv.addObject("file","hop.xls");
		mv.setViewName("system/hop/uploadexcel");
		return mv;
	}
	
	/**excel导入数据库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file
			)throws Exception{
		ModelAndView mv = this.getModelAndView();
		List<LocDisease> list = ExcelImportUtil.importExcel(file.getInputStream(),LocDisease.class,new ImportParams());
		Map<String,Object> parMap=new HashMap<>();
		for(LocDisease hop:list){
			if(StringUtils.isBlank(hop.getDiseaseName()))continue;
			if(StringUtils.isBlank(hop.getSYSNAME())) continue;
			parMap.clear();
			parMap.put("LOC_NAME", hop.getLocName());
			List<AppLoc> locs=commonService.selectByEqCon(AppLoc.class, parMap);
			if(locs.size()==0)continue;
			parMap.clear();
			parMap.put("DISEASE_NAME", hop.getDiseaseName());
			List<AppDisease> diseases=commonService.selectByEqCon(AppDisease.class, parMap);
			if(diseases.size()==0)continue;
			parMap.clear();
			parMap.put("LOC_ID", locs.get(0).getLocId());
			parMap.put("DISEASE_ID", diseases.get(0).getDiseaseId());
			List<LocDisease> locDiseases=commonService.selectByEqCon(LocDisease.class, parMap);
			if(locDiseases.size()>0) continue;
			hop.setDiseaseId(diseases.get(0).getDiseaseId());
			hop.setLocId(locs.get(0).getLocId());
			commonService.saveOrUpdate(hop);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/excel")
	public String excel(ModelMap map)throws Exception{
		
		Page page=new Page();
		page.setShowCount(100000);
		page.setPd(this.getPageData());
		List<LocDisease> locDiseases=locDiseaseService.listPage(page);
        map.put(NormalExcelConstants.FILE_NAME,"疾病科室对照信息");
        map.put(NormalExcelConstants.CLASS,LocDisease.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("疾病科室对照列表", "导出人:大熊小清新", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,locDiseases);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

}
