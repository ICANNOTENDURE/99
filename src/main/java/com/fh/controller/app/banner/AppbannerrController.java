package com.fh.controller.app.banner;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(value = "contacts-api", tags = "获取首页图片的操作") 
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
	@ApiOperation(notes = "getAppBanner",  value = "获取首页图片列表")
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "APP_TOKEN", value = "登陆用的token", required = false, dataType = "String"),
		@ApiImplicitParam(name = "APP_USER_CODE", value = "登陆用的用户名", required = false, dataType = "String")
	})
	@RequestMapping(value="/getAppBanner",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getAppBanner(){
		
		JsonResult jsonResult=new JsonResult();
		List<BannerVO> list=new ArrayList<BannerVO>();
		jsonResult.setDatas(list);
		try {
			GeneralQueryParam queryParam=new GeneralQueryParam();
			Map<String,Object> map=new HashMap<String, Object>();
			List<String> strings=new ArrayList<String>();
			queryParam.setConditionExp(" banner_Status=#{conditionParam.bannerStatus} and banner_type=#{conditionParam.bannerType}");
			map.put("bannerStatus", "Y");
			map.put("bannerType", "1");
			queryParam.setConditionParam(map);
			queryParam.setOrderExp(" banner_Seq desc ");
			strings.add("banner_Img");
			strings.add("banner_Id");
			strings.add("banner_Title");
			strings.add("banner_link_url");
			queryParam.setQueryColumn(strings);
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
				list.add(bannerVO);
			}
			
		} catch (Exception e) {
			jsonResult.setCode(11);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@ApiOperation(notes = "listDetail",  value = "显示健康明细")
	@ApiImplicitParam(name = "id", value = "文章明显id", required = true, dataType = "String")
	@RequestMapping(value="/listDetail/{id}",method = RequestMethod.GET)
	public ModelAndView listDetail(@PathVariable String id) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("app/banner/detail");
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		mv.addObject("pd", appBanner.getBannerContent());
		return mv;
	}
	

}
	
 