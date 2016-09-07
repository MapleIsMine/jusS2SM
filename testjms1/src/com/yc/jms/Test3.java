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

// 每个消息直被消费了一次，但是如果有多个消费者同时监听一个Queue的话，无法确定一个消息最终会被哪一个消费者消费。
public class Test3 {

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
        
        //注册第一个消费者
        MessageConsumer comsumer1 = session.createConsumer(queue); 
        comsumer1.setMessageListener(   new MessageListener(){
			@Override
			public void onMessage(Message message) {
				//消息类型:  TextMessage, ObjectMessage,  StreamMessage, MapMessage,bytesMessage
				TextMessage text=(TextMessage)message;
				try {
					System.out.println(   "消费者一号:"+ text.getText()  );
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
        });
        //注册第二个消费者
        MessageConsumer comsumer2 = session.createConsumer(queue); 
        comsumer2.setMessageListener(   new MessageListener(){
			@Override
			public void onMessage(Message message) {
				//消息类型:  TextMessage, ObjectMessage,  StreamMessage, MapMessage,bytesMessage
				TextMessage text=(TextMessage)message;
				try {
					System.out.println(  "消费者二号:"+text.getText()  );
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
        });
          
      
      //创建一个生产者
        MessageProducer producer = session.createProducer(queue); 
        for(  int i=0;i<10;i++){
	        //通过会话来创建一个消息
	        Message message = session.createTextMessage("消息编号:"+ i );  
	        //生产者将消息发给服务器
	        producer.send(message);  
        }
       
         
       
        
        
        
        
        
       

	}

}
