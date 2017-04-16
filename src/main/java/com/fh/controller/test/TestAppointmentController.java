package com.fh.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.test.record.impl.TestAppointmentService;
import com.fh.util.PageData;
@Controller
@RequestMapping(value="/testappoint")
public class TestAppointmentController extends BaseController{

	@Autowired
	private TestAppointmentService testAppointmentService;
	
	/**显示列表
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("test/appoint/list");
		PageData pd=getPageData();
		page.setPd(pd);
		mv.addObject("list", testAppointmentService.list(page));
		mv.addObject("pd", pd);
		return mv;
	}
	
}
