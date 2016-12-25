package com.fh.entity.app;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;
@Table(name="APP_BANNER")
public class AppBanner extends BaseEntity implements Serializable{
	
	/**
	 * 
	 */@Transient
	private static final long serialVersionUID = -2714259307417714269L;
	@Id
	private String bannerId;
	private String bannerImg;
	private String bannerContent;
	private Integer bannerSeq;
	private String bannerStatus;
	private String bannerTitle;
	//1首页图片
	//2健康咨询
	private String bannerType;
	private String bannerLinkUrl;
	
	
	
	
	
	
	public String getBannerLinkUrl() {
		return bannerLinkUrl;
	}
	public void setBannerLinkUrl(String bannerLinkUrl) {
		this.bannerLinkUrl = bannerLinkUrl;
	}
	public String getBannerType() {
		return bannerType;
	}
	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}
	public String getBannerTitle() {
		return bannerTitle;
	}
	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getBannerImg() {
		return bannerImg;
	}
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}
	public String getBannerContent() {
		return bannerContent;
	}
	public void setBannerContent(String bannerContent) {
		this.bannerContent = bannerContent;
	}
	public Integer getBannerSeq() {
		return bannerSeq;
	}
	public void setBannerSeq(Integer bannerSeq) {
		this.bannerSeq = bannerSeq;
	}
	public String getBannerStatus() {
		return bannerStatus;
	}
	public void setBannerStatus(String bannerStatus) {
		this.bannerStatus = bannerStatus;
	}
	
	
}
