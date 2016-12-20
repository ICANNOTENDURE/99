package com.fh.controller.system.loc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppLoc;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;

@Controller
@RequestMapping(value="/loc")
public class AppLocController extends BaseController{
	
	
	@Autowired
	private CommonService commonService;
	
	
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		try{
			Map<String, Object> conditionParam = new HashMap<String, Object>();
			String locid=getPageData().get("parent")==null?"0":getPageData().get("parent").toString();
			conditionParam.put("loc_parent", locid);
			page.setConditionExp("loc_parent = #{conditionParam.loc_parent}");
			page.setConditionParam(conditionParam);
			mv.setViewName("system/loc/list");
			mv.addObject("list", commonService.listPage(AppLoc.class, page));
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
		if("0".equals(parent)){
			mv.addObject("parentName", "根");
		}else{
			mv.addObject("parentName", commonService.selectByPrimaryKey(AppLoc.class, parent).getLocName());
		}
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
	
		String conditionExp = "loc_name = #{conditionParam.loc_name}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("loc_name", account);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		List<AppLoc> lists=commonService.selectAdvanced(AppLoc.class, queryParam);
		return new JsonResult(lists.size(),"");
	}
}
