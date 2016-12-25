package com.fh.controller.system.loc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

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
import com.fh.entity.app.AppLoc;
import com.fh.entity.vo.Select;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.LocService;

@Controller
@RequestMapping(value="/loc")
public class AppLocController extends BaseController{
	
	
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
		try{
			mv.setViewName("system/loc/list");
			String locid=getPageData().get("parent")==null?"0":getPageData().get("parent").toString();
			page.setConditionExp("LOC_PARENT = #{conditionParam.LOC_PARENT}");
			Map<String, Object> conditionParam = new HashMap<String, Object>();
			conditionParam.put("LOC_PARENT", locid);
			page.setConditionParam(conditionParam);
			String keywords=getPageData().get("keywords")==null?"":getPageData().get("keywords").toString();
			if(StringUtils.isNotBlank(keywords)){
				page.getLikeExpMap().put("loc_name", keywords);			
			}

			mv.addObject("list", commonService.listPage(AppLoc.class, page));
			mv.addObject("keywords", keywords);
			if(!locid.equals("0")){
				mv.addObject("pd", commonService.selectByPrimaryKey(AppLoc.class, locid));
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping(value="/tree")
	public ModelAndView tree(Page page){
		ModelAndView mv = getModelAndView();
		try{
			List<AppLoc> rootList=new ArrayList<AppLoc>();
			Map<String, AppLoc> locMap = new LinkedHashMap<String, AppLoc>();
			List<AppLoc> list=commonService.selectAdvanced(AppLoc.class, new GeneralQueryParam());
			for(AppLoc loc:list){
				if(loc.getLocParent().equals("0")){
					rootList.add(loc);
				}
				locMap.put(loc.getLocId(), loc);
			}
			//遍历Map缕清父子关系
			Iterator<String> keys = locMap.keySet().iterator();
			while(keys.hasNext()){			
				String key = keys.next();		
				AppLoc loc = locMap.get(key);	
				if(!loc.getLocParent().equals("0")){
					AppLoc parentLoc = locMap.get(loc.getLocParent());
					parentLoc.getNodes().add(loc);
				}
				loc.setUrl("loc/list.do?parent="+loc.getLocId());
				loc.setTarget("treeFrame");
			}
			JSONArray arr = JSONArray.fromObject(rootList);
			String json = arr.toString();
			json = json.replaceAll("locId", "id").replaceAll("locParent", "pId").replaceAll("locName", "name");
			mv.setViewName("system/loc/tree");
			mv.addObject("zTreeNodes", json);
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
	public ModelAndView goSaveOrUpdate(String id,String parent) throws Exception{
		ModelAndView mv =getModelAndView();
		AppLoc appLoc=new AppLoc();
		mv.setViewName("system/loc/edit");
		parent=StringUtils.isBlank(parent)?"0":parent;
		mv.addObject("locList",commonService.selectAdvanced(AppLoc.class, new GeneralQueryParam()));
		if(StringUtils.isNotBlank(id)){
			appLoc=commonService.selectByPrimaryKey(AppLoc.class, id);
		}else{
			appLoc.setLocParent(parent);
		}

		mv.addObject("pd", appLoc);
		return mv;
	}
	
	/**保存或者新增
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(AppLoc appLoc) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
	
		commonService.saveOrUpdate(appLoc);
		mv.setViewName("save_result");
		return mv;
	}

	/**检查是否重复
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/checkAccount/{account}")
	@ResponseBody
	public JsonResult checkAccount(@PathVariable String account) throws Exception{
		account = new String(account.getBytes("ISO-8859-1"),"UTF-8");
		return new JsonResult(locService.getByName(account).size(),"");
	}

	
	//下载
	@RequestMapping(value="/excel")
	public String excel(ModelMap map)throws Exception{
		
		Page page=new Page();
		page.setShowCount(100000);
		page.setPd(this.getPageData());
		List<AppLoc> appHops=commonService.listPage(AppLoc.class, page);
        map.put(NormalExcelConstants.FILE_NAME,"科室信息");
        map.put(NormalExcelConstants.CLASS,AppLoc.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("医院列表", "导出人:大熊小清新", "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,appHops);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**进入上传excel界面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception{
		ModelAndView mv =getModelAndView();
		mv.addObject("path","loc/readExcel.do");
		mv.addObject("file","loc.xls");
		mv.setViewName("system/hop/uploadexcel");
		return mv;
	}
	
	/**excel导入数据库
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(@RequestParam(value="excel",required=false) MultipartFile file )throws Exception{
		ModelAndView mv = this.getModelAndView();
		List<AppLoc> list = ExcelImportUtil.importExcel(file.getInputStream(),AppLoc.class,new ImportParams());
		for(AppLoc loc:list){
			if(StringUtils.isBlank(loc.getLocName()))continue;
			loc.setLocStatus("Y");
			List<AppLoc> appLocs=locService.getByName(loc.getLocName());
			if(appLocs.size()>0){
				loc.setLocId(appLocs.get(0).getLocId());
				loc.setLocParent(appLocs.get(0).getLocParent());
			}else{
				loc.setLocParent("0");
			}
			commonService.saveOrUpdate(loc);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	
	/*科室下拉列表使用*/
	@RequestMapping(value="/locSelect")
	@ResponseBody
	public List<Select> locSelect(String search) throws Exception{
		

		List<Select> mv=new ArrayList<Select>();
		List<String> colmn=new ArrayList<String>();
		colmn.add("LOC_ID");
		colmn.add("LOC_NAME");
		GeneralQueryParam queryParam=new GeneralQueryParam();
		queryParam.setPageNo(1);
		queryParam.setPageSize(10);
		queryParam.setQueryColumn(colmn);
		if(StringUtils.isNotBlank(search)){
			search = new String(search.getBytes("ISO-8859-1"),"UTF-8");
			queryParam.setConditionExp(" LOC_NAME like #{conditionParam.LOC_NAME} ");
			Map<String, Object> conditionParam = new HashMap<String, Object>();
			conditionParam.put("LOC_NAME", "%"+search.trim()+"%");
			queryParam.setConditionParam(conditionParam);
		}
		List<Map<String, Object>> list=commonService.selectAdvancedByColumn(AppLoc.class, queryParam);
		for (Map<String, Object> mapping : list) {
			mv.add(new Select(mapping.get("LOC_ID").toString(), mapping.get("LOC_NAME").toString()));
		}
		return mv;
	}
}
