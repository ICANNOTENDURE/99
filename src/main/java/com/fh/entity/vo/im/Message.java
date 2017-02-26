package com.fh.entity.vo.im;

import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(value = {"patId" ,"docId"})
public class Message {
	
	@ApiModelProperty(value = "token")
	private String token;
	@ApiModelProperty(value = "提问id")
	private String askId;
	@ApiModelProperty(value = "接收消息用户id")
	private String toUser;
	@ApiModelProperty(value = "文本消息")
	private String msg;
	@ApiModelProperty(value = "消息类型，0:初始化websocket,1:文本，2:图片，3:语音")
	private String msgType;
	@ApiModelProperty(value = "图片")
	private String img;
	@ApiModelProperty(value = "缩略图")
	private String thumbImg;
	@ApiModelProperty(value = "发送消息人类型,1:医生,2:病人")
	private String sendUserType;
	
	@ApiModelProperty(hidden=true)
	private String patId;
	@ApiModelProperty(hidden=true)
	private String docId;
	
	
	
	
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getSendUserType() {
		return sendUserType;
	}
	public void setSendUserType(String sendUserType) {
		this.sendUserType = sendUserType;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getThumbImg() {
		if(StringUtils.isNotBlank(img)){
			thumbImg="thumbnail."+img;
		}
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
	
	
	
}
