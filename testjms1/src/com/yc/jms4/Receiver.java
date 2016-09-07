package com.yc.jms4;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 真正使用服务器 这个类用于消费消息
 */
public class Receiver {

	public static void main(String[] args) throws JMSException {
		// JDBC: DriverManager.getConnection("","","");
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");
		Connection connection = factory.createConnection();
		connection.start();

		// 创建会话                                                                                                  false表示可以回送信息
		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建一个目的地,它用来接收消息
		Destination destination = session.createQueue("testQueue1");

		// 创建消费者
		MessageConsumer comsumer = session.createConsumer(destination);
		comsumer.setMessageListener(new MessageListener() {
			public void onMessage(Message m) {
				try {
					// 输出接收到的消息
					System.out.println("Consumer get " + ((TextMessage) m).getText());
					System.out.println("回复的目标:" + m.getJMSReplyTo());
					// 发送一个回复
					// 创建一个新的MessageProducer来发送一个回复消息。
					MessageProducer producer = session.createProducer(m.getJMSReplyTo()); // m.getJMSReplyTo()
																							// 取出回复消息的队列
					producer.send(session.createTextMessage("Hello i'm receiver"));
					System.out.println("回复成功");
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

	}

}
