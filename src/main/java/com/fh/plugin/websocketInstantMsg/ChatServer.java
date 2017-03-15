package com.fh.plugin.websocketInstantMsg;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;

import com.alibaba.fastjson.JSON;
import com.fh.entity.system.pat.PatAskSub;
import com.fh.entity.vo.im.Message;
import com.fh.entity.vo.token.Token;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.enums.MessageType;
import com.fh.util.enums.UserType;
import com.fh.util.security.AESCoder;


/**
 * 即时通讯
 */
public class ChatServer extends WebSocketServer{
	
	protected Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private CommonService commonService;
	private static final ConcurrentHashMap<String,WebSocket> users;
	static{
		users = new ConcurrentHashMap<String,WebSocket>();
	}
	
	public ChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public ChatServer(InetSocketAddress address) {
		super(address);
	}

	/**
	 * 触发连接事件
	 */
	@Override
	public void onOpen( WebSocket conn, ClientHandshake handshake ) {
		logger.error("onOpen:");
		if(commonService==null){
			commonService=(CommonService)ContextLoader.getCurrentWebApplicationContext().getBean("commonService");
		}
	}

	/**
	 * 触发关闭事件
	 */
	@Override
	public void onClose( WebSocket conn, int code, String reason, boolean remote ) {
	
        for (Map.Entry<String, WebSocket> m :users.entrySet())  {  
        	if(m.getValue().isClosed()){
        		users.remove(m.getKey());
        	}
        }
	}

	/**
	 * 客户端发送消息到服务器时触发事件
	 */
	@Override
	public void onMessage(WebSocket conn, String message){
		
		Message msg=JSON.parseObject(message.toString(),Message.class);
		String str=AESCoder.aesCbcDecrypt(msg.getToken(), Const.APP_TOKEN_KEY);
		Token token=JSON.parseObject(str, Token.class);
		if(UserType.DOC.getType().equals(token.getAccounttType())){
			users.put(token.getInfoId(), conn);
		}else{
			users.put(token.getAccount(), conn);
		}
		if("0".equals(msg.getMsgType())){
			return;
		}
		try {
			saveMsg(msg,token);
			if(users.containsKey(msg.getToUser())){
				if(users.get(msg.getToUser()).isOpen()){ 
					users.get(msg.getToUser()).send(message);
				}else{
					users.remove(msg.getToUser());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void onFragment( WebSocket conn, Framedata fragment ) {
		System.out.println(22);
	}

	/**
	 * 触发异常事件
	 */
	@Override
	public void onError( WebSocket conn, Exception ex ) {
		ex.printStackTrace();
		if(conn!=null){
			//conn.close(org.java_websocket.server.WebSocketServer.DECODERS);
		}
        for (Map.Entry<String, WebSocket> m :users.entrySet())  {  
        	if(m.getValue().isClosed()){
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

