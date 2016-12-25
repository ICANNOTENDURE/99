package com.fh.controller.app.banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class AppbannerrController extends BaseController {
    
	@Autowired
	private CommonService commonService;
	
	/**
	 * 首页图片信息
	 * @return
	 * APP_TOKEN=bWnKvSbBZDx+9pnE/XzI1w==
	 * APP_USER_CODE=13919007855
	 */
	@RequestMapping(value="/getAppBanner")
	@ResponseBody
	public Object getAppBanner(){
		
		if(!checkToken()) return new JsonResult(100,"token错误");
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
			queryParam.setQueryColumn(strings);
			List<Map<String, Object>> maps=commonService.selectAdvancedByColumn(AppBanner.class, queryParam);
			for (Map<String, Object> mapping : maps) {
				BannerVO bannerVO=new BannerVO();
				bannerVO.setImg_url(Const.APP_URL+Const.FILEPATHFILE+mapping.get("banner_Img").toString());
				bannerVO.setTitle(mapping.get("banner_Title").toString());
				bannerVO.setUrl(Const.APP_URL+"appbanner/listDetail/"+mapping.get("banner_Id").toString());
				list.add(bannerVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonResult;
	}
	
	@RequestMapping(value="/listDetail/{id}")
	public ModelAndView listDetail(@PathVariable String id) throws Exception{
		ModelAndView mv = getModelAndView();
		mv.setViewName("app/banner/detail");
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		mv.addObject("pd", appBanner.getBannerContent());
		return mv;
	}
	
}
	
 