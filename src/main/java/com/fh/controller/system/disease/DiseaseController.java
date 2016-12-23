package com.fh.controller.system.disease;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.Select;
import com.fh.entity.app.AppDisease;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.LocService;
import com.fh.util.Const;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/disease")
public class DiseaseController extends BaseController{

	@Autowired
	private CommonService commonService;
	@Autowired
	private LocService locService;
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
			mv.setViewName("system/disease/list");
			page.setConditionExp("DISEASE_NAME  like #{conditionParam.DISEASE_NAME}");
			String DISEASE_NAME=getPageData().get("keywords")==null?"":getPageData().get("keywords").toString();
			page.getConditionParam().put("DISEASE_NAME","%"+DISEASE_NAME+"%");
			mv.addObject("list", commonService.listPage(AppDisease.class, page));
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**去新增或者修改页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goSaveOrUpdate")
	public ModelAndView goSaveOrUpdate(String id) throws Exception{
		ModelAndView mv =getModelAndView();
		AppDisease appDisease=new AppDisease();
		mv.setViewName("system/disease/edit");
		if(StringUtils.isNotBlank(id)){
			appDisease=commonService.selectByPrimaryKey(AppDisease.class, id);
		}
		mv.addObject("pd", appDisease);
		return mv;
	}
	
	/**保存或者新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(AppDisease appDisease) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		commonService.saveOrUpdate(appDisease);
		mv.setViewName("save_result");
		return mv;
	}

	/**检查是否重复
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkName")
	@ResponseBody
	public JsonResult checkName(String name) throws Exception{
		
		name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
		return new JsonResult(getByName(name).size(),"");
	}
	public List<AppDisease> getByName(String name) throws Exception{
		
		String conditionExp = "disease_Name = #{conditionParam.disease_Name}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("disease_Name", name);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		return commonService.selectAdvanced(AppDisease.class, queryParam);
	
	}
	/**进入上传excel界面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception{
		ModelAndView mv =getModelAndView();
		mv.addObject("path","disease/readExcel.do");
		mv.addObject("file","disease.xls");
		mv.setViewName(Const.UPLOADEXCEL);
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
		List<AppDisease> list = ExcelImportUtil.importExcel(file.getInputStream(),AppDisease.class,new ImportParams());
		for(AppDisease disease:list){
			if(StringUtils.isBlank(disease.getDiseaseName()))continue;
			disease.setDiseaseStatus("Y");
			List<AppDisease> diseases=getByName(disease.getDiseaseName());
			if(diseases.size()>0){
				disease.setDiseaseId(diseases.get(0).getDiseaseId());
			}
			commonService.saveOrUpdate(disease);
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
        map.put(NormalExcelConstants.FILE_NAME,"疾病信息");
        map.put(NormalExcelConstants.CLASS,AppDisease.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("医院列表", "导出人:大熊小清新", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,commonService.listPage(AppDisease.class, page));
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/*疾病下拉列表使用*/
	@RequestMapping(value="/diseaseSelect")
	@ResponseBody
	public List<Select> diseaseSelect(String search) throws Exception{
		

		List<Select> mv=new ArrayList<Select>();
		List<String> colmn=new ArrayList<String>();
		colmn.add("DISEASE_ID");
		colmn.add("DISEASE_NAME");
		GeneralQueryParam queryParam=new GeneralQueryParam();
		queryParam.setPageNo(1);
		queryParam.setPageSize(10);
		queryParam.setQueryColumn(colmn);
		if(StringUtils.isNotBlank(search)){
			search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
			queryParam.setConditionExp(" DISEASE_NAME like #{conditionParam.DISEASE_NAME} ");
			Map<String, Object> conditionParam = new HashMap<String, Object>();
			conditionParam.put("DISEASE_NAME", "%"+search.trim()+"%");
			queryParam.setConditionParam(conditionParam);
		}
		List<Map<String, Object>> list=commonService.selectAdvancedByColumn(AppDisease.class, queryParam);
		for (Map<String, Object> mapping : list) {
			mv.add(new Select(mapping.get("DISEASE_ID").toString(), mapping.get("DISEASE_NAME").toString()));
		}
		return mv;
	}
}
