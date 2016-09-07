package com.yc.mailservice.mailservicejms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;


//消费者监听接口
@Component("consumerMessageListener")
public class ConsumerMessageListener implements SessionAwareMessageListener<MapMessage> {

	@Override
	public void onMessage(MapMessage message, Session session) throws JMSException {
		//取出传过来的消息
		String email=message.getString("email");
		
		System.out.println( message+"\n "+email );
		//TODO:发送邮件
		
		Destination replyDestination=message.getJMSReplyTo();   //从传过来的消息中取出回送的地址
		
		//创建一个会话，通过会话建立 与 jms服务器的联接， 以回送数据
		MessageProducer producer=session.createProducer(replyDestination);   //并将数据写入到jms服务器中的回送队列
		
		Message replyMessage=session.createTextMessage("code:1");
		
		producer.send(  replyMessage   );
	}

}
