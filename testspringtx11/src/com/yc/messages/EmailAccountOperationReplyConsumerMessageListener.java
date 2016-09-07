package com.yc.messages;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component("emailAccountOperationReplyConsumerMessageListener")
public class EmailAccountOperationReplyConsumerMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		TextMessage textMessage=(TextMessage) message;
		//TODO: 接收到回复的信息
		try {
			System.out.println( "操作结果:"+ textMessage.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
