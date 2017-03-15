package com.fh.controller.app.banner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.app.AppBanner;
import com.fh.entity.vo.app.BannerVO;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;



@Controller 
@RequestMapping(value="/appbanner")
@Api(value = "首页baner操作", tags = "首页baner操作") 
public class AppbannerrController extends BaseController {
    
	@Autowired
	private CommonService commonService;
	
	/**
	 * 首页图片信息
	 * @return
	 * APP_TOKEN=bWnKvSbBZDx+9pnE/XzI1w==
	 * 			 bWnKvSbBZDx+9pnE/XzI1w==
	 * APP_USER_CODE=13919007855
	 */
	@ApiOperation(notes = "获取首页图片列表",  value = "获取首页图片列表")
	@RequestMapping(value="/getAppBanner",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<BannerVO> getAppBanner(
			@ApiParam(name = "type",value="新闻类型,0：病人首页图片，1：病人新闻列表，2医生首页图片") @RequestParam String type,
			@ApiParam(name = "SHOW_COUNT",value="一页的显示条数,传空默认为10") @RequestParam Integer SHOW_COUNT,
			@ApiParam(name = "CURRENT_PAGE",value="当前页数,不传默认为1") @RequestParam Integer CURRENT_PAGE){
		
		JsonResult<BannerVO> jsonResult=new JsonResult<BannerVO>();
		List<BannerVO> list=new ArrayList<BannerVO>();
		jsonResult.setDatas(list);
		try {
			GeneralQueryParam queryParam=new GeneralQueryParam();
			Map<String,Object> map=new HashMap<String, Object>();
			List<String> strings=new ArrayList<String>();
			queryParam.setConditionExp(" banner_Status=#{conditionParam.bannerStatus} and banner_type=#{conditionParam.bannerType}");
			map.put("bannerStatus", "Y");
			map.put("bannerType", type);
			queryParam.setConditionParam(map);
			queryParam.setOrderExp(" banner_Seq desc ");
			strings.add("banner_Img");
			strings.add("banner_Id");
			strings.add("banner_Title");
			strings.add("banner_link_url");
			queryParam.setQueryColumn(strings);
			queryParam.setPageNo(CURRENT_PAGE);
			queryParam.setPageSize(SHOW_COUNT);
			List<Map<String, Object>> maps=commonService.selectAdvancedByColumn(AppBanner.class, queryParam);
			for (Map<String, Object> mapping : maps) {
				BannerVO bannerVO=new BannerVO();
				String img=mapping.get("banner_Img")==null?"":mapping.get("banner_Img").toString();
				bannerVO.setImg_url(Const.APP_URL+Const.FILEPATHIMG+img);
				String title=mapping.get("banner_Title")==null?"":mapping.get("banner_Title").toString();
				bannerVO.setTitle(title);
				//bannerVO.setUrl(Const.APP_URL+"appbanner/listDetail/"+mapping.get("banner_Id").toString());
				String linkUrl=mapping.get("banner_link_url")==null?"":mapping.get("banner_link_url").toString();
				bannerVO.setUrl(linkUrl);
				bannerVO.setId(mapping.get("banner_Id").toString());
				list.add(bannerVO);
			}
			
		} catch (Exception e) {
			jsonResult.setCode(11);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@ApiOperation(notes = "显示健康资讯明细",  value = "显示健康资讯明细")
	@ApiImplicitParam(name = "id", value = "文章明显id", required = true, dataType = "String")
	@RequestMapping(value="/listDetail/{id}",method = RequestMethod.GET)
	public ModelAndView listDetail(@PathVariable String id) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("app/banner/detail");
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		mv.addObject("pd", appBanner.getBannerContent());
		return mv;
	}
	
	@ApiOperation(notes = "查询新闻明细",  value = "查询新闻明细")
	@RequestMapping(value="/getBannerDetail",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppBanner> getBannerDetail(
			@ApiParam(name = "id",value="id",required=false) @RequestParam String id) throws Exception{
		
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		JsonResult<AppBanner> jsonResult=new JsonResult<AppBanner>();
		jsonResult.getDatas().add(appBanner);
		return jsonResult;
	}
	
}
	
 