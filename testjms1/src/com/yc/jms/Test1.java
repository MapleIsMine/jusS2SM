package com.yc.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

//入门案例： 这里用的是本机作为消息的存储，没有使用activemq服务器
public class Test1 {

	public static void main(String[] args) throws JMSException {
		//建立与消息服务器的连接
		ConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");  
        Connection connection = factory.createConnection();  
        connection.start();  
          
        //Queue是 jms接口  ->           ActiveMQQueue
        //创建一个队列
        Queue queue = new ActiveMQQueue("testQueue"); 
        //创建一个会话.
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
        //通过会话来创建一个消息
        Message message = session.createTextMessage("Hello JMS!");  
         //创建一个生产者
        MessageProducer producer = session.createProducer(queue); 
        //生产者将消息发给服务器
        producer.send(message);  
     
        System.out.println("消息已经发送到服务器");  
         
        //创建一个消费者
        MessageConsumer comsumer = session.createConsumer(queue); 
        //消费者接收消息
        Message recvMessage = comsumer.receive();  
        System.out.println(((TextMessage)recvMessage).getText());  

	}

}
