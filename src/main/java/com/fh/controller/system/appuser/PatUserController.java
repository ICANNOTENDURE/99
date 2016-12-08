package com.fh.controller.system.appuser;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.system.appuser.impl.PatUserService;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;

@Controller
@RequestMapping(value="/patuser")
public class PatUserController extends BaseController{
	
	
	@Autowired
	private PatUserService patUserService;
	
	/**显示病人列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/listUsers")
	public ModelAndView listUsers(Page page){
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	userList = patUserService.listPdPageUser(page);		
			mv.setViewName("system/appuser/patuser_list");
			mv.addObject("userList", userList);
			mv.addObject("pd", pd);
			mv.addObject("QX",Jurisdiction.getHC());	//按钮权限
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
	public ModelAndView saveOrUpdate() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		if(StringUtils.isBlank(pd.getString("USER_ID"))){
			pd.put("USER_ID", this.get32UUID());
			patUserService.save(pd);
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
