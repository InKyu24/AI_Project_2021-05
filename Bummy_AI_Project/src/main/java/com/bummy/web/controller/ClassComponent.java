package com.bummy.web.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.bummy.web.util.SessionList;


//webRTC.html과 연결
@Component
@ServerEndpoint(value = "/class")
public class ClassComponent {
	
	ArrayList<Session> list;
	@OnOpen
	public void open(Session session) {
		list=SessionList.getInstance().list;
		synchronized (list) {
			list.add(session);
		}
		System.out.println(session.getId()+" 접속 ok");
	}
	
	@OnMessage
	public void receiveMsg(String msg) {
		System.out.println("받은 메시지: "+msg+" "+list.size()+"명에게 전송합니다");
		//broadcast
		for(Session session:list) {
			// 사용자들의 주소 얻고, msg 보내기
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@OnMessage
	
	@OnClose
	public void close(Session session) {
		synchronized (list) {
			list.remove(session);
		}
		System.out.println(session.getId()+" 접속 end");
	}
}
