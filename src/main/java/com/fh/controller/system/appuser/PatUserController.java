package com.fh.controller.system.appuser;

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
import com.fh.entity.system.PatUser;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.appuser.impl.PatUserService;

@Controller
@RequestMapping(value="/patuser")
public class PatUserController extends BaseController{
	
	
	@Autowired
	private PatUserService patUserService;
	
	@Autowired
	private CommonService commonService;
	
	/**显示病人列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page){
		ModelAndView mv = getModelAndView();
		try{
			mv.setViewName("system/appuser/patuser_list");
			mv.addObject("userList", commonService.listPage(PatUser.class, page));
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**去新增用户页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/goSaveOrUpdate")
	public ModelAndView goSaveOrUpdate(String id) throws Exception{
		ModelAndView mv =getModelAndView();
		PatUser patUser=new PatUser();
		mv.setViewName("system/appuser/patuser_edit");
		if(StringUtils.isNotBlank(id)){
			patUser=commonService.selectByPrimaryKey(PatUser.class, id);
		}
		mv.addObject("pd", patUser);
		return mv;
	}
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(PatUser patUser) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
	
		commonService.saveOrUpdate(patUser);
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public JsonResult delete(@PathVariable String id) throws Exception{
		
		commonService.deleteByPrimaryKey(PatUser.class, id);
		return new JsonResult();
	}
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteAll/{ids}")
	@ResponseBody
	public JsonResult deleteAll(@PathVariable String ids) throws Exception{
	
		String condition = "user_id in #{conditionParam.userid}";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userid", "("+ids+")");
		commonService.deleteByCondition(PatUser.class,condition,param);
		return new JsonResult();
	}
	
	@RequestMapping(value="/checkAccount/{account}")
	@ResponseBody
	public JsonResult checkAccount(@PathVariable String account) throws Exception{
	
		String conditionExp = "user_account = #{conditionParam.user_account}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("user_account", account);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		List<PatUser> patUsers=commonService.selectAdvanced(PatUser.class, queryParam);
		return new JsonResult(patUsers.size(),"");
	}
}
