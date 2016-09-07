package com.yc.jms2;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 真正使用服务器
 * 这个类用于消费消息
 */
public class Receiver {

	public static void main(String[] args) throws JMSException {
		//JDBC:   DriverManager.getConnection("","","");
		ConnectionFactory factory = new ActiveMQConnectionFactory(  ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
        Connection connection = factory.createConnection();  
        connection.start();  
        
        //创建会话
        Session session=connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建一个目的地,它用来接收消息: 这里创建的是一个Queue,表明我们用的是  p2p方式,    另一种Destination是  Topic,用于订阅模式
        Destination destination=session.createQueue(  "testQueue"  );
        //创建消费者
        MessageConsumer consumer=session.createConsumer(destination);
        //取出发过来的消息
        Message message = consumer.receive();
        if(  message!=null ){
        	TextMessage text=(TextMessage)message;
        	System.out.println(  "接收到了:"+  text );
        }
        
        
	}

}
