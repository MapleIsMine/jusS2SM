package com.yc.jms5;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * SessionAwareMessageListener：是由spring提供，它可以在回调方法中传入session，以此回送信息到生产者
 * @author Administrator
 *
 */
@Component("consumerMessageListener2")
public class ConsumerMessageListener2 implements SessionAwareMessageListener<TextMessage> {

	private Destination destination;

	@Override
	public void onMessage(TextMessage message, Session session) throws JMSException {
			System.out.println("接收到的消息是一个文本消息:"+ message.getText());
			//通过session 创建  producer对象，再回送信息
			//从message中取出信息回送的目的地,以便创建生产者.
			MessageProducer producer=session.createProducer( message.getJMSReplyTo()   );
			//创建一条消息
			Message textMessage=session.createTextMessage(  "生产者发过来的信息已经处理完毕，game over..."   );   
			//调用发送
			producer.send(textMessage);
	}

	@Resource(name="sendQueueDestination")
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	

	
	
}
