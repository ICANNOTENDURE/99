package com.fh.controller.system.appuser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.PatUser;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.appuser.impl.PatUserService;
import com.fh.util.PageData;

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
		PageData pd = getPageData();
		try{
			page.setPd(pd);	
			mv.setViewName("system/appuser/patuser_list");
			mv.addObject("userList", patUserService.listPdPageUser(page));
			mv.addObject("pd", pd);
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
	public ModelAndView goSaveOrUpdate() throws Exception{
		ModelAndView mv =getModelAndView();
		PageData pd =getPageData();
		mv.setViewName("system/appuser/patuser_edit");
		if(StringUtils.isNotBlank(pd.getString("USER_ID"))){
			pd = patUserService.findById(pd);
		}
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**保存用户
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveOrUpdate")
	public ModelAndView saveOrUpdate(PatUser patUser) throws Exception{
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		if(StringUtils.isBlank(pd.getString("USER_ID"))){
			pd.put("USER_ID", this.get32UUID());
			//patUserService.save(patUser);
			commonService.insert(patUser);
		}else{
			patUserService.edit(pd);
		}
		mv.setViewName("save_result");
		return mv;
	}
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete() throws Exception{
		PageData pd = this.getPageData();
		patUserService.delete(pd);
		return pd;
	}
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() throws Exception{
		PageData pd = this.getPageData();
		patUserService.deleteAll(pd.getString("USER_IDS").split(","));
		return pd;
	}
}
