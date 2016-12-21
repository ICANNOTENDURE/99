package com.fh.controller.system.hop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.fh.entity.app.AppHop;
import com.fh.entity.system.Dictionaries;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.HopService;
import com.fh.service.system.dictionaries.impl.DictionariesService;

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
	@RequestMapping(value="/checkName/{name}")
	@ResponseBody
	public JsonResult checkName(@PathVariable String name) throws Exception{
	
		String conditionExp = "hop_Name = #{conditionParam.hop_Name}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("hop_Name", name);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		List<AppHop> lists=commonService.selectAdvanced(AppHop.class, queryParam);
		return new JsonResult(lists.size(),"");
	}
}
