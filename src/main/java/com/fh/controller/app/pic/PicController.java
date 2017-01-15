package com.fh.controller.app.pic;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.service.common.impl.CommonService;
import com.fh.util.DateUtil;
import com.fh.util.FileUpload;
import com.fh.util.PathUtil;

@Controller
@RequestMapping(value="/apppic")
@Api(value = "上传图片", tags = "上传图片") 
public class PicController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	@ApiOperation(notes = "ajax上传文件",  value = "ajax上传文件")
	@RequestMapping(value="/upload",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> upload(HttpServletRequest request,HttpServletResponse 
			response) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator<String> fileNames = multipartRequest.getFileNames();
	    MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
		String fileName = FileUpload.fileUp(multipartFile, PathUtil.PicPath(), DateUtil.getDays()+get32UUID());//执行上传
		return new JsonResult<Object>(0,PathUtil.PicPath()+fileName);
	}
	

}
