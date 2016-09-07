package com.yc.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class Test2 {

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
        Message message = session.createTextMessage("Hello world!!!");  
         //创建一个生产者
        MessageProducer producer = session.createProducer(queue); 
        //生产者将消息发给服务器
        producer.send(message);  
     
        System.out.println("消息已经发送到服务器");  
         
        //创建一个消费者
        MessageConsumer comsumer = session.createConsumer(queue); 
        comsumer.setMessageListener(   new MessageListener(){
			@Override
			public void onMessage(Message message) {
				//消息类型:  TextMessage, ObjectMessage,  StreamMessage, MapMessage,bytesMessage
				TextMessage text=(TextMessage)message;
				try {
					System.out.println(  text.getText()  );
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
        });
        
        
        
        
        
       

	}

}
