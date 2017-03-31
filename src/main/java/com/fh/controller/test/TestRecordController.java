package com.fh.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.test.record.impl.TestRecordService;
import com.fh.util.PageData;
@Controller
@RequestMapping(value="/testrecord")
public class TestRecordController extends BaseController{

	@Autowired
	private TestRecordService testRecordService;
	
	/**显示列表
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("test/record/list");
		PageData pd=getPageData();
		page.setPd(pd);
		mv.addObject("list", testRecordService.list(page));
		mv.addObject("pd", pd);
		return mv;
	}
	
}
