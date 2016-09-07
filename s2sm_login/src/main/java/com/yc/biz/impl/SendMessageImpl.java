package com.yc.biz.impl;

import java.util.HashMap;
import java.util.Map;

import com.yc.biz.SendMessage;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;

@Service()
public class SendMessageImpl implements SendMessage {
	private JmsTemplate jmsTemplate;
    private Queue sendDestination;
    private Queue replyDestination;
   
    @Resource(name="jmsTemplate")
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Resource(name="sendDestination")
	public void setSendDestination(Queue sendDestination) {
		this.sendDestination = sendDestination;
	}

	@Resource(name="replyDestination")
	public void setReplyDestination(Queue replyDestination) {
		this.replyDestination = replyDestination;
	}

	public void sendMail(Map<String,String> message) {
		    jmsTemplate.convertAndSend(sendDestination, message, new MessagePostProcessor() {
		        public Message postProcessMessage(Message message) throws JMSException {
		        	message.setJMSReplyTo(   replyDestination);   //通过这个message,告诉消费者它的回送的响应放在   replyDestination
		            return message;
		        }
		    });
	}

}
