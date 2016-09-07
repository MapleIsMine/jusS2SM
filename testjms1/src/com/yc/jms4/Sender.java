package com.yc.jms4;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

//如何接收回复的消息
public class Sender {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");

		Connection connection = factory.createConnection();
		connection.start();

		// 消息发送到这个Queue
		Queue queue = new ActiveMQQueue("testQueue1");
		// 消息回复到这个Queue
		Queue replyQueue = new ActiveMQQueue("replyQueue1");

		final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建一个消息，
		Message message = session.createTextMessage("   i'm zy...");
		// 并设置它的JMSReplyTo为replyQueue。
		message.setJMSReplyTo(replyQueue); // 通过这句代码指定回复的消息传到哪个队列
		// 创建生产者
		MessageProducer producer = session.createProducer(queue);
		// 设置消息的保存模式: 持久化的
		producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		// 发送消息
		producer.send(message);
		System.out.println("消息发送成功");

		// 创建一个消费者, 来获取接收者回复的消息
		MessageConsumer comsumer2 = session.createConsumer(replyQueue);
		comsumer2.setMessageListener(new MessageListener() {
			public void onMessage(Message m) {
				try {
					System.out.println("接收到的回复信息:"+((TextMessage) m).getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
