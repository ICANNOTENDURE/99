package com.fh.controller.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppHop;
import com.fh.entity.test.AppTest;
import com.fh.service.common.impl.CommonService;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
@Controller
@RequestMapping(value="/testcat")
public class TestCatController extends BaseController{

	@Autowired
	private CommonService commonService;
	
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
			mv.setViewName("test/cat/list");
			Map<String, Object> eqParam=new HashMap<String, Object>();
			Map<String, Object> lkParam=new HashMap<String, Object>();
			if(StringUtils.isNotBlank(getPar("name"))){
				lkParam.put("name", "%"+getPar("name").trim()+"%");
				page.setLkParam(lkParam);
				mv.addObject("name", getPar("name"));
			}
			if(StringUtils.isNotBlank(getPar("hopId"))){
				eqParam.put("hop_Id", getPar("hopId"));
				page.setEqParam(eqParam);
				mv.addObject("hopId", getPar("hopId"));
				AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, getPar("hopId"));
				if(appHop!=null){
					mv.addObject("hopName", appHop.getHopName());
				}
			}
			List<AppTest> appTests=commonService.listPage(AppTest.class, page);
			for(AppTest appTest:appTests){
				AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, appTest.getHopId());
				if(appHop!=null){
					appTest.setHopName(appHop.getHopName());
				}
			}
			mv.addObject("list", appTests);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/goSaveOrUpdate")
	public ModelAndView goSaveOrUpdate(String id) throws Exception {
	
		ModelAndView mv = getModelAndView();
		if("1".equals(super.getPar("isCreate"))){
			mv.setViewName("test/cat/create");
		}else{
			mv.setViewName("test/cat/edit");
		}
		if(StringUtils.isNotBlank(id)){
			AppTest appTest=commonService.selectByPrimaryKey(AppTest.class, id);
			if(StringUtils.isNotBlank(appTest.getHopId())){
				AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, appTest.getHopId());
				if(appHop!=null){
					appTest.setHopName(appHop.getHopName());
				}
			}
			mv.addObject("pd", appTest);
			mv.addObject("date", DateUtil.getDay());
			mv.addObject("eddate", DateUtil.getAfterDayDate("7"));
		}
		return mv;
	}
	
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(AppTest appTest) throws Exception {
		ModelAndView mv = this.getModelAndView();
		commonService.saveOrUpdate(appTest);
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public JsonResult<Object> delete(@PathVariable String id) throws Exception{

		commonService.deleteByPrimaryKey(AppTest.class, id);
		return new JsonResult<Object>();
	}
	
	@RequestMapping(value="/saveCreate", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> saveCreate(String id,Date startDate,Date endDate) throws Exception {
		
		JsonResult<Object> jsonResult=new JsonResult<>();
		long day=0;
		day=(endDate.getTime()-startDate.getTime())/(24*60*60*1000);
		if(day<0){
			jsonResult.setMessage("开始日期不能小于结束日期");
		}else{
			AppTest appTest=commonService.selectByPrimaryKey(AppTest.class, id);
			if("Y".equals(appTest.getStatus())){
				Map<String, String> weekMap=new HashMap<String, String>();
				if("1".equals(appTest.getMonday())){weekMap.put("星期一", "星期一");}
				if("1".equals(appTest.getTuesday())){weekMap.put("星期二", "星期二");}
				if("1".equals(appTest.getWednesday())){weekMap.put("星期三", "星期三");}
				if("1".equals(appTest.getThursday())){weekMap.put("星期四", "星期四");}
				if("1".equals(appTest.getFriday())){weekMap.put("星期五", "星期五");}
				if("1".equals(appTest.getSaturday())){weekMap.put("星期六", "星期六");}				
				if("1".equals(appTest.getSunday())){weekMap.put("星期日", "星期日");}
				String week="";
				int count=0;
				SimpleDateFormat sdf = new SimpleDateFormat("E",Locale.CHINESE);
				Calendar cal=Calendar.getInstance();
				cal.setTime(startDate);	
				for(long i=0;i<=day;i++){
				    week = sdf.format(cal.getTime());
				    if(weekMap.containsKey(week)){
				    	count++;
				    }
				    cal.add(Calendar.DATE, 1);
				}
				jsonResult.setCode(0);
				jsonResult.setMessage("生成"+count+"条记录");
			}else{
				jsonResult.setMessage("排班字典不可用");
			}
		}
		return jsonResult;
	}
}
