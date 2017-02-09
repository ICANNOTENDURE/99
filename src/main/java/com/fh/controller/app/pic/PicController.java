package com.fh.controller.app.pic;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.FileOutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
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
		DefaultMultipartHttpServletRequest multipartRequest = (DefaultMultipartHttpServletRequest) request;
		
		Iterator<String> fileNames = multipartRequest.getFileNames();
	    MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
		String fileName = FileUpload.fileUp(multipartFile, PathUtil.PicPath(), DateUtil.getDays()+get32UUID());//执行上传
		
		return new JsonResult<Object>(0,Const.APP_IMG_PATH+fileName);
	}
	
   public static void toSmaillImg(String filePath,String thumbPath) throws Exception{   
        String newurl =thumbPath;   
        java.awt.Image bigJpg = javax.imageio.ImageIO.read(new java.io.File(filePath));   
        float tagsize = 100;   
        int old_w = bigJpg.getWidth(null);   
        int old_h = bigJpg.getHeight(null);      
        int new_w = 0;   
       int new_h = 0;   
        float tempdouble;    
        tempdouble = old_w > old_h ? old_w/tagsize : old_h/tagsize;   
       new_w = Math.round(old_w/tempdouble);   
        new_h = Math.round(old_h/tempdouble);   
        java.awt.image.BufferedImage tag = new java.awt.image.BufferedImage(new_w,new_h,java.awt.image.BufferedImage.TYPE_INT_RGB);   
        tag.getGraphics().drawImage(bigJpg,0,0,new_w,new_h,null);   
        FileOutputStream newimage = new FileOutputStream(newurl);   
       com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(newimage);          
        encoder.encode(tag);   
        newimage.close();   
    }
}
