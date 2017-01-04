package com.fh.plugin.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.alibaba.fastjson.JSON;
import com.fh.entity.app.AppBanner;
import com.fh.entity.vo.im.Message;
import com.fh.service.common.impl.CommonService;

public class WebsocketEndPoint extends TextWebSocketHandler {
	
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
			users.put(msg.getFromUser(), session);
			if(users.containsKey(msg.getToUser())){
				if(users.get(msg.getToUser()).isOpen()){
					users.get(msg.getToUser()).sendMessage(message);
				}else{
					users.remove(msg.getToUser());
				}
			}else{
				AppBanner t=new AppBanner();
				t.setBannerLinkUrl(msg.getMsg());
				commonService.saveOrUpdate(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
    

}
