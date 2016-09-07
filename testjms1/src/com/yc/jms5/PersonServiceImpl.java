package com.yc.jms5;

import javax.annotation.Resource;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("personServiceImpl")
public class PersonServiceImpl implements PersonService {

	private Destination destination;
	private JmsTemplate jsmTemplate;

	@Override
	public void sendMessage(final String message) {
		System.out.println("生产者发送消息"+ message);
		
		//回调
		jsmTemplate.send(destination, new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createTextMessage(message);
				msg.setJMSDeliveryMode(   DeliveryMode.NON_PERSISTENT   );
				return msg;
			}
		});
	}

	@Resource(name="queueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	@Resource(name="jmsTemplate")
	public void setJsmTemplate(JmsTemplate jsmTemplate) {
		this.jsmTemplate = jsmTemplate;
	}

}
