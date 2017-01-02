package com.fh.entity.vo.im;

public class Message {
	
	
	private String token;
	private String askId;
	private String fromUser;
	private String toUser;
	private String msg;
	/*
	 * 消息类型
	 * 1:文本
	 * 2:图片
	 * 3:语言
	 */
	private String msgType;
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
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	
	
	
}
