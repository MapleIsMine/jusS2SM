package com.yc.jms2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 真正使用服务器
 * 这个类用于生产消息
 */
public class Sender {

	public static void main(String[] args) throws JMSException {
		//JDBC:   DriverManager.getConnection("","","");
		ConnectionFactory factory = new ActiveMQConnectionFactory(  ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();  
        
        //创建会话
        Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建一个目的地,它用来接收消息: 这里创建的是一个Queue,表明我们用的是  p2p方式,    另一种Destination是  Topic,用于订阅模式
        Destination destination=session.createQueue(  "testQueue"  );
        //创建生产者
        MessageProducer producer = session.createProducer(destination); 
        //指定消息的保存发送模式
        producer.setDeliveryMode(   DeliveryMode.NON_PERSISTENT );
        //创建一个要发送的消息:   文本类型的消息
        Message message = session.createTextMessage("Hello JMS!");
        //发送
        producer.send(message);
        
        //提交会话
        session.commit();
        System.out.println("消息发送成功");
        
	}

}
