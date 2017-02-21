package com.fh.resolver;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.fh.entity.JsonResult;

/**
 * 
 * 类名称：MyExceptionResolver.java 类描述：
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ex.printStackTrace();
		// 判断是否 Ajax 请求
		if ((request.getHeader("accept").indexOf("application/json") > -1 || 
			(request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))
			||(request.getHeader("Content-Type").indexOf("application/json") > -1)) {

			response.setContentType("text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer;
			try {
				writer = response.getWriter();
				String msg=JSON.toJSONString(ex.getStackTrace());
				//String msg=JSON.toJSONString(ex.);
				writer.write(JSON.toJSONString(new JsonResult<>(999, msg)));
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
			return mv;
		}

	}

}
