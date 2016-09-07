package com.yc.messages;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.yc.bean.AccountOperation;

@Service("emailAccountOperationMessageProducerServiceImpl")
public class EmailAccountOperationMessageProducerServiceImpl implements EmailAccountOperationMessageProducerService {
	private Destination sendDestination; // 用于存发送信息的队列
	private JmsTemplate jmsTemplate; // jms操作模板
	private Destination replyDestination; // 用于存回复信息的队列,

	@Override
	public void sendMessage(final AccountOperation accountOperation) {
		// 回调
		jmsTemplate.send(sendDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				//TODO:发送信息到activemq
				//1. 将  accountOperation 转为 json字符串
				Gson gson=new Gson();
				String info=gson.toJson(accountOperation);
				TextMessage msg=session.createTextMessage( info);
				// 设置回复的信息的目的地.
				msg.setJMSReplyTo(replyDestination);
				// 设置发送的信息类型 为非持久化信息
				msg.setJMSDeliveryMode(DeliveryMode.PERSISTENT);
				return msg;
			}
		});
	}

	@Resource(name = "replyDestination")
	public void setReplyDestination(Destination replyDestination) {
		this.replyDestination = replyDestination;
	}

	@Resource(name = "sendDestination")
	public void setDestination(Destination destination) {
		this.sendDestination = destination;
	}

	@Resource(name = "jmsTemplate")
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

}
