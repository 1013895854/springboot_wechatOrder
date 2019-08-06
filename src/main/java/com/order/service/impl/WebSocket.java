package com.order.service.impl;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
	private Session session;
	
	private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
	
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocketSet.add(this);
		log.info("[WebSocket消息]有新的链接，总数:{}",webSocketSet.size());
	}
	
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		log.info("[WebSocket消息]断开链接，总数:{}",webSocketSet.size());
		
	}
	
	@OnMessage
	public void onMessage(String message) {
		log.info("[WebSocket消息]收到客户端发来的消息，内容:{}",message);
	}
	
	public void sendMessage(String message) {
		for(WebSocket  websocket : webSocketSet) {
			log.info("[webSocket消息]发送消息，内容:{}",message);
			try {
				websocket.session.getBasicRemote().sendText(message);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
}
