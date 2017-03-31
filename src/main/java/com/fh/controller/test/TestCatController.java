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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppHop;
import com.fh.entity.test.AppTestRecord;
import com.fh.entity.test.AppTestResourse;
import com.fh.entity.test.AppTestTemplate;
import com.fh.service.common.impl.CommonService;
import com.fh.service.test.record.impl.TestTemplateService;
import com.fh.util.DateUtil;
import com.fh.util.PageData;
import com.fh.util.enums.WeekEnum;
@Controller
@RequestMapping(value="/testcat")
public class TestCatController extends BaseController{

	@Autowired
	private CommonService commonService;
	@Autowired
	private TestTemplateService testTemplateService;
	
	/**显示列表
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		ModelAndView mv = getModelAndView();
		PageData pd=getPageData();
		page.setPd(pd);
		page.setShowCount(70);
		try{
			mv.setViewName("test/cat/list");
			mv.addObject("list", testTemplateService.list(page));
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
			AppTestResourse appTestResourse=commonService.selectByPrimaryKey(AppTestResourse.class, id);
			if(StringUtils.isNotBlank(appTestResourse.getHopId())){
				AppHop appHop=commonService.selectByPrimaryKey(AppHop.class, appTestResourse.getHopId());
				if(appHop!=null){
					appTestResourse.setHopName(appHop.getHopName());
				}
			}
			castTestWeek(appTestResourse);
			mv.addObject("pd", appTestResourse);
			mv.addObject("date", DateUtil.getDay());
			mv.addObject("eddate", DateUtil.getAfterDayDate("7"));
		}
		return mv;
	}
	
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(AppTestResourse appTest) throws Exception {
		ModelAndView mv = this.getModelAndView();
		commonService.saveOrUpdate(appTest);
		String condition = "resourse_Id = #{conditionParam.resourseId}";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourseId",appTest.getId());
		commonService.deleteByCondition(AppTestTemplate.class,condition,param);

		
	
		AppTestTemplate appTestTemplate=new AppTestTemplate();
		appTestTemplate.setFlag(appTest.getMonday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Mon.getCode());
		appTestTemplate.setQty(appTest.getMondayQty());
		commonService.saveOrUpdate(appTestTemplate);
		

		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getTuesday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Tues.getCode());
		appTestTemplate.setQty(appTest.getTuesdayQty());
		commonService.saveOrUpdate(appTestTemplate);	
	

		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getWednesday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Wed.getCode());
		appTestTemplate.setQty(appTest.getWednesdayQty());
		commonService.saveOrUpdate(appTestTemplate);
		

		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getThursday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Thur.getCode());
		appTestTemplate.setQty(appTest.getThursdayQty());
		commonService.saveOrUpdate(appTestTemplate);
				

		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getFriday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Fri.getCode());
		appTestTemplate.setQty(appTest.getFridayQty());
		commonService.saveOrUpdate(appTestTemplate);
			
	
		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getSaturday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Sat.getCode());
		appTestTemplate.setQty(appTest.getSaturdayQty());
		commonService.saveOrUpdate(appTestTemplate);
	

		appTestTemplate.setId(null);
		appTestTemplate.setFlag(appTest.getSunday());
		appTestTemplate.setResourseId(appTest.getId());
		appTestTemplate.setDay(WeekEnum.Sun.getCode());
		appTestTemplate.setQty(appTest.getSundayQty());
		commonService.saveOrUpdate(appTestTemplate);

		mv.setViewName("save_result");
		return mv;
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
			AppTestResourse appTest=commonService.selectByPrimaryKey(AppTestResourse.class, id);
			if("Y".equals(appTest.getStatus())){
				castTestWeek(appTest);
				Map<String, Integer> weekMap=new HashMap<String, Integer>();
				Map<String, Object> parMap=new HashMap<String, Object>();
				if("1".equals(appTest.getMonday())){weekMap.put("星期一", appTest.getMondayQty());}
				if("1".equals(appTest.getTuesday())){weekMap.put("星期二", appTest.getTuesdayQty());}
				if("1".equals(appTest.getWednesday())){weekMap.put("星期三", appTest.getWednesdayQty());}
				if("1".equals(appTest.getThursday())){weekMap.put("星期四", appTest.getThursdayQty());}
				if("1".equals(appTest.getFriday())){weekMap.put("星期五", appTest.getFridayQty());}
				if("1".equals(appTest.getSaturday())){weekMap.put("星期六", appTest.getSaturdayQty());}				
				if("1".equals(appTest.getSunday())){weekMap.put("星期日", appTest.getSundayQty());}
				String week="";
				int count=0;
				SimpleDateFormat sdf = new SimpleDateFormat("E",Locale.CHINESE);
				Calendar cal=Calendar.getInstance();
				cal.setTime(startDate);	
				for(long i=0;i<=day;i++){
				    week = sdf.format(cal.getTime());
				    if(weekMap.containsKey(week)){
				    	parMap.clear();
				    	parMap.put("resource_Id", appTest.getId());
				    	parMap.put("date", cal.getTime());
				    	List<AppTestRecord> appTestRecords=commonService.selectByEqCon(AppTestRecord.class, parMap);
				    	if(appTestRecords.size()==0){
				    		AppTestRecord appTestRecord=new AppTestRecord();
				    		appTestRecord.setQty(weekMap.get(week));
				    		appTestRecord.setPrice(appTest.getPrice());
				    		appTestRecord.setDate(cal.getTime());
				    		appTestRecord.setResourceId(appTest.getId());
				    		commonService.saveOrUpdate(appTestRecord);
				    		count++;
				    	}
				    	
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
	
	private void castTestWeek(AppTestResourse appTestResourse) throws Exception{
		
		Map<String, Object> templatesMap=new HashMap<String, Object>();
		templatesMap.clear();
		templatesMap.put("resourse_Id", appTestResourse.getId());
		List<AppTestTemplate> templates=commonService.selectByEqCon(AppTestTemplate.class, templatesMap);
		for(AppTestTemplate appTestTemplate:templates){
			if(WeekEnum.Mon.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setMonday(appTestTemplate.getFlag());
				appTestResourse.setMondayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Tues.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setTuesday(appTestTemplate.getFlag());
				appTestResourse.setTuesdayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Wed.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setWednesday(appTestTemplate.getFlag());
				appTestResourse.setWednesdayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Thur.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setThursday(appTestTemplate.getFlag());
				appTestResourse.setThursdayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Fri.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setFriday(appTestTemplate.getFlag());
				appTestResourse.setFridayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Sat.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setSaturday(appTestTemplate.getFlag());
				appTestResourse.setSaturdayQty(appTestTemplate.getQty());
			}
			if(WeekEnum.Sun.getCode().equals(appTestTemplate.getDay())){
				appTestResourse.setSunday(appTestTemplate.getFlag());
				appTestResourse.setSundayQty(appTestTemplate.getQty());
			}
		}
	}
}
