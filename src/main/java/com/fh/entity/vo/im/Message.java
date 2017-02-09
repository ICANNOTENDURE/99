package com.fh.entity.vo.im;

import org.apache.commons.lang.StringUtils;

public class Message {
	
	
	private String token;
	private String askId;
	private String toUser;
	private String fromUser;
	private String msg;
	/*
	 * 消息类型
	 * 1:文本
	 * 2:图片
	 * 3:语言
	 */
	private String msgType;
	/*
	 * 1:发送
	 * 2:接收
	 */
	private String sendType;
	private String img;
	//接收图片的时候的缩略图
	private String thumbImg;
	/*
	 * 1:医生
	 * 2:病人
	 */
	private String sendUserType;
	
	
	
	
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
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getThumbImg() {
		if(StringUtils.isNotBlank(img)){
			thumbImg="THUMB_"+img;
		}
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
	
	
	
}
