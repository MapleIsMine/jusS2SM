package com.yc.jms5;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//临听器有两种实现方案: 一种是采用原生的jms的MessageListener
//                    另一种是采用spring的方案:SessionAwareMessageListener 

//注意: 这里的MessageListener接口是  jms的接口
public class ConsumerMessageListener1 implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		if(  message instanceof TextMessage){
			TextMessage text=(TextMessage) message;
			System.out.println("接收到的消息是一个文本消息:"+ text);
		}
	}

}
