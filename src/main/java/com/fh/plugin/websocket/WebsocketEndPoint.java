package com.fh.plugin.websocket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.system.pat.PatAskSub;
import com.fh.entity.vo.im.Message;
import com.fh.entity.vo.token.Token;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.security.AESCoder;

public class WebsocketEndPoint extends TextWebSocketHandler {
	
	
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CommonService commonService;
	
	private static final ConcurrentHashMap<String,WebSocketSession> users;
	static{
		users = new ConcurrentHashMap<String,WebSocketSession>();
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		try {
			logger.debug("input:"+message.getPayload().toString());
			Message msg=JSON.parseObject(message.getPayload().toString(),Message.class);
			
			String str=AESCoder.aesCbcDecrypt(msg.getToken(), Const.APP_TOKEN_KEY);
			logger.debug("token:"+str);
			Token token=JSON.parseObject(str, Token.class);
			getImg(msg, token);
			users.put(token.getAccount(), session);
			saveMsg(msg,token);
			if(users.containsKey(msg.getToUser())){
				if(users.get(msg.getToUser()).isOpen()){
					users.get(msg.getToUser()).sendMessage(message);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("err:"+e.getMessage());
		}
		
	}


	
	@Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            session.close();
        }
        for (Map.Entry<String, WebSocketSession> m :users.entrySet())  {  
        	if(m.getValue().getId().equals(session.getId())){
        		users.remove(m.getKey());
        	}
        }
    }
	    
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        for (Map.Entry<String, WebSocketSession> m :users.entrySet())  {  
        	if(m.getValue().getId().equals(session.getId())){
        		users.remove(m.getKey());
        	}
        }
    }
    
    //保存聊天记录
    public void saveMsg(Message msg,Token token) throws Exception{
		PatAskSub askSub=new PatAskSub();
		askSub.setAsksubDate(new Date());
		askSub.setParentId(msg.getAskId());
		askSub.setAsksubType(token.getAccounttType());
		askSub.setAsksubMessageType(msg.getMsgType());
		if("1".equals(msg.getMsgType())){
			askSub.setAsksubContent(msg.getMsg());
		}else{
			askSub.setAsksubPath(msg.getMsg());
		}
		commonService.saveOrUpdate(askSub);
    }
    
    public void getImg(Message msg,Token token) throws Exception{
    	String imgPath="";
    	if(StringUtils.isBlank(msg.getFrUserImg())){
    		if("1".equals(token.getAccounttType())){
    			DocUser docUser=commonService.selectByPrimaryKey(DocUser.class, token.getAccount());
    			DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, docUser.getDocId());
    			imgPath=docInfo.getDocPic();
    		}else{
    			//PatUser patUser=commonService.selectByPrimaryKey(PatUser.class, token.getAccount());
    		}
			imgPath=StringUtils.isBlank(imgPath)?"empty.png":imgPath;
			msg.setFrUserImg(Const.APP_IMG_PATH+imgPath);
    	}
    	if(StringUtils.isBlank(msg.getToUserImg())){
    		imgPath="";
    		if("1".equals(token.getAccounttType())){
    			DocInfo docInfo=commonService.selectByPrimaryKey(DocInfo.class, msg.getToUser());
    			imgPath=docInfo.getDocPic();
    		}else{
    			
    		}
    		imgPath=StringUtils.isBlank(imgPath)?"empty.png":imgPath;
			msg.setToUserImg(Const.APP_IMG_PATH+imgPath);
    	}
    }
}
