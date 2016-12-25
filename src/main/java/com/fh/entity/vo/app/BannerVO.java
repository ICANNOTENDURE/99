package com.fh.entity.vo.app;

import io.swagger.annotations.ApiModelProperty;

public class BannerVO {
	
	@ApiModelProperty(value = "新闻首页图片url")
	private String img_url;
	@ApiModelProperty(value = "新闻链接页面url")
	private String url;
	@ApiModelProperty(value = "新闻图片id")
	private String id;
	@ApiModelProperty(value = "新闻图片标题")
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
