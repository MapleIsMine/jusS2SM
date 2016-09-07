package com.yc.messages;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.yc.bean.AccountOperation;
import com.yc.biz.OpMailSender;


@Component("emailAccountOperationMessageConsumerListener")
public class EmailAccountOperationMessageConsumerListener implements SessionAwareMessageListener<TextMessage> {
	private OpMailSender opMailSender;

	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
		//AccountOperation accountOperation=(AccountOperation) message.getObject();
		//System.out.println("接收到的邮箱信息为:"+accountOperation+"，下面开始邮件发送");
		
		//接收json类型的信息
		String info=message.getText();
		Gson gson=new Gson();
		AccountOperation ao=gson.fromJson(info, AccountOperation.class);
		
		this.opMailSender.sendOpEmail(ao);
		
		//创建一个生产者，用于生产响应信息
		MessageProducer producer=session.createProducer( message.getJMSReplyTo()   );
		//创建一条消息
		Message textMessage=session.createTextMessage( "发送邮件至"+  ao.getEmail()+"成功"   );   
		//调用发送
		producer.send(textMessage);
	}

	@Resource(name="opMailSenderImpl")
	public void setOpMailSender(OpMailSender opMailSender) {
		this.opMailSender = opMailSender;
	}
}
