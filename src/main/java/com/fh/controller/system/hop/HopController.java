package com.fh.controller.system.hop;

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
import com.fh.entity.app.AppHop;
import com.fh.entity.system.Dictionaries;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.HopService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/hop")
public class HopController extends BaseController{
	
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private HopService hopService;
	
	@Autowired
	private DictionariesService dictionariesService;
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		try{

			mv.setViewName("system/hop/list");
			mv.addObject("list", hopService.listPage(page));

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
		AppHop appHop=new AppHop();
		mv.setViewName("system/hop/edit");
		if(StringUtils.isNotBlank(id)){
			appHop=commonService.selectByPrimaryKey(AppHop.class, id);
		}
		mv.addObject("pd", appHop);
		List<Dictionaries> dictionaries=dictionariesService.listSubDictByParentId("120197ba4bc84d32a0d86191bcdfff56");
		mv.addObject("levelList", dictionaries);
		return mv;
	}
	
	/**保存或者新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(AppHop appHop) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		commonService.saveOrUpdate(appHop);
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
	public List<AppHop> getByName(String name) throws Exception{
		
		String conditionExp = "hop_Name = #{conditionParam.hop_Name}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("hop_Name", name);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		return commonService.selectAdvanced(AppHop.class, queryParam);
	
	}
	/**进入上传excel界面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception{
		ModelAndView mv =getModelAndView();
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
		List<AppHop> list = ExcelImportUtil.importExcel(file.getInputStream(),AppHop.class,new ImportParams());
		Map<String,String> dicMap=new HashMap<>();
		for(AppHop hop:list){
			if(StringUtils.isBlank(hop.getHopName()))continue;
			if(StringUtils.isBlank(hop.getLevelDesc())) continue;
			if(dicMap.containsKey(hop.getLevelDesc())){
				hop.setHopLevel(dicMap.get(hop.getLevelDesc()));
			}else{
				PageData data=dictionariesService.findByName(hop.getLevelDesc());
				if(data.get("DICTIONARIES_ID")!=null){
					hop.setHopLevel(data.get("DICTIONARIES_ID").toString());
					dicMap.put(hop.getLevelDesc(), data.get("DICTIONARIES_ID").toString());
				}
			}
			hop.setHopStatus("Y");
			List<AppHop> appHops=getByName(hop.getHopName());
			if(appHops.size()>0){
				hop.setHopId(appHops.get(0).getHopId());
			}
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
		List<AppHop> appHops=hopService.listPage(page);
        map.put(NormalExcelConstants.FILE_NAME,"医院信息");
        map.put(NormalExcelConstants.CLASS,AppHop.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("医院列表", "导出人:大熊小清新", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,appHops);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}
