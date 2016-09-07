package com.yc.jms5;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("personServiceImpl2")
public class PersonServiceImpl2 implements PersonService {
	private Destination destination;   //用于存发送信息的队列
	private JmsTemplate jsmTemplate;   //jms操作模板
	private Destination replyDestination;   //用于存回复信息的队列,
	@Override
	public void sendMessage(final String message) {
		System.out.println("生产者2->发送消息" + message);
		// 回调
		jsmTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createTextMessage(message);
				// 设置回复的信息的目的地.
				msg.setJMSReplyTo(replyDestination);
				// 设置发送的信息类型 为非持久化信息
				msg.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);

				//创建一个消费者，用于接收对方回复的信息   注意，这个消费者临听   replyDestination
				MessageConsumer comsumer2 = session.createConsumer(replyDestination);
				comsumer2.setMessageListener(new MessageListener() {
					public void onMessage(Message m) {
						try {
							System.out.println("接收到的回复信息:" + ((TextMessage) m).getText());
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				});
				return msg;
			}
		});
	}
	@Resource(name = "replyQueueDestination")
	public void setReplyDestination(Destination replyDestination) {
		this.replyDestination = replyDestination;
	}
	@Resource(name = "sendQueueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Resource(name = "jmsTemplate")
	public void setJsmTemplate(JmsTemplate jsmTemplate) {
		this.jsmTemplate = jsmTemplate;
	}

}
