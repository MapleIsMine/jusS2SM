package com.yc.jms6;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;


@Component("emailConsumerMessageListener")
public class EmailConsumerMessageListener implements SessionAwareMessageListener<ObjectMessage> {
	

	@Override
	public void onMessage(ObjectMessage message, Session session) throws JMSException {
		AccountOperation accountOperation=(AccountOperation) message.getObject();
		System.out.println("接收到的邮箱信息为:"+accountOperation+"，下面开始邮件发送");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//创建一个生产者，用于生产响应信息
		MessageProducer producer=session.createProducer( message.getJMSReplyTo()   );
		//创建一条消息
		Message textMessage=session.createTextMessage( "发送邮件至"+  accountOperation.getEmail()+"成功"   );   
		//调用发送
		producer.send(textMessage);
	}


}
