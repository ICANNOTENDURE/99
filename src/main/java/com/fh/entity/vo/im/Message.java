package com.fh.entity.vo.im;

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
	private String toUserImg;
	private String frUserImg;
	/*
	 * 1:发送
	 * 2:接收
	 */
	private String sendType;
	//接收图片的时候的缩略图
	private String thumbImg;
	
	
	public String getToUserImg() {
		return toUserImg;
	}
	public void setToUserImg(String toUserImg) {
		this.toUserImg = toUserImg;
	}
	public String getFrUserImg() {
		return frUserImg;
	}
	public void setFrUserImg(String frUserImg) {
		this.frUserImg = frUserImg;
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
		return thumbImg;
	}
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}
	
	
	
	
}
