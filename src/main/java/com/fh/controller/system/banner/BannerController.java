package com.fh.controller.system.banner;

import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.app.AppBanner;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.DelAllFile;
import com.fh.util.FileUpload;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.enums.BannerEnum;
@Controller
@RequestMapping(value="/banner")
public class BannerController extends BaseController{

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
			mv.setViewName("system/banner/list");
			mv.addObject("list", commonService.listPage(AppBanner.class, page));
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	@RequestMapping(value="/goSaveOrUpdate")
	public ModelAndView goSaveOrUpdate(String id) throws Exception {
	
		ModelAndView mv = getModelAndView();
		mv.setViewName("system/banner/edit");
		String type="";
		if(StringUtils.isNotBlank(id)){
			AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
			type=appBanner.getBannerType();
			mv.addObject("pd", appBanner);
		}
		mv.addObject("bannerType",BannerEnum.getCombo(type));
		mv.addObject("APP_URL",Const.APP_URL);
		return mv;
	}
	
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(HttpServletRequest request,
			@RequestParam(value="tp",required=false) MultipartFile file,AppBanner appBanner) {
		ModelAndView mv = this.getModelAndView();
		try {
			if(file!=null){
				String fileName = FileUpload.fileUp(file, PathUtil.PicPath(), DateUtil.getDays()+get32UUID());//执行上传
				Thumbnails.of(PathUtil.PicPath()+fileName)
		        .size(200, 200)
		        .toFile(PathUtil.PicPath()+"thumbnail."+fileName);
				appBanner.setBannerImg(fileName);
			}
			commonService.saveOrUpdate(appBanner);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/deltp")
	@ResponseBody
	public JsonResult<Object> deltp(String id) throws Exception{
		
		JsonResult<Object> jsonResult=new JsonResult<Object>();
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		DelAllFile.delFolder(PathUtil.PicPath()+appBanner.getBannerImg());
		appBanner.setBannerImg("");
		commonService.saveOrUpdate(appBanner);
		return jsonResult;
	}
	@RequestMapping(value="/delete/{id}")
	@ResponseBody
	public JsonResult<Object> delete(@PathVariable String id) throws Exception{
		
		JsonResult<Object> jsonResult=new JsonResult<Object>();
		AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, id);
		if(StringUtils.isNotBlank(appBanner.getBannerImg())){
			DelAllFile.delFolder(PathUtil.PicPath()+  appBanner.getBannerImg());
		}
		commonService.deleteByPrimaryKey(AppBanner.class, id);
		return jsonResult;
	}
	
	@RequestMapping(value="/deleteAll/{ids}")
	@ResponseBody
	public JsonResult<Object> deleteAll(@PathVariable String ids) throws Exception{
		
		JsonResult<Object> jsonResult=new JsonResult<Object>();
		String[] arr=ids.split(",");
		for(int i=0;i<arr.length;i++){
			AppBanner appBanner=commonService.selectByPrimaryKey(AppBanner.class, arr[i]);
			if(StringUtils.isNotBlank(appBanner.getBannerImg())){
				DelAllFile.delFolder(PathUtil.PicPath()+ appBanner.getBannerImg());
			}
			commonService.deleteByPrimaryKey(AppBanner.class, arr[i]);
		}
		return jsonResult;
	}
}
