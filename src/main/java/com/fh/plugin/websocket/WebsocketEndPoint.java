package com.fh.plugin.websocket;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.fh.entity.system.pat.PatAskSub;
import com.fh.entity.vo.im.Message;
import com.fh.entity.vo.token.Token;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.enums.MessageType;
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
			Message msg=JSON.parseObject(message.getPayload().toString(),Message.class);
			String str=AESCoder.aesCbcDecrypt(msg.getToken(), Const.APP_TOKEN_KEY);
			Token token=JSON.parseObject(str, Token.class);
			users.put(token.getAccount(), session);
			saveMsg(msg,token);
			if(users.containsKey(msg.getToUser())){
				if(users.get(msg.getToUser()).isOpen()){ 
					TextMessage toMessage=new TextMessage(JSON.toJSONString(msg));
					users.get(msg.getToUser()).sendMessage(toMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
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
		//发送类型
		askSub.setAsksubType(token.getAccounttType());
		//消息类型
		askSub.setAsksubMessageType(msg.getMsgType());
		//文本图片消息
		if((MessageType.TEXT.getType()).equals(msg.getMsgType())){
			askSub.setAsksubContent(msg.getMsg());
		}else{
			askSub.setAsksubPath(msg.getMsg());
		}
		commonService.saveOrUpdate(askSub);
    }
}
