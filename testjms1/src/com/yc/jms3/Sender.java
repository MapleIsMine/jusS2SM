package com.yc.jms3;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

/**
 * 测试消息的类型 : DeliveryMode.PERSISTENT
 *               DeliveryMode.NON_PERSISTENT
 */
public class Sender {

	public static void main(String[] args) throws JMSException {
		//JDBC:   DriverManager.getConnection("","","");
		ConnectionFactory factory = new ActiveMQConnectionFactory(  ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD, "tcp://localhost:61616");  
	     
        Connection connection = factory.createConnection();  
        connection.start();  
         
        Queue queue = new ActiveMQQueue("testQueue");  
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);  
                 
        //第一组消息   persistent消息
        MessageProducer producer = session.createProducer(queue);  
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
        producer.send(session.createTextMessage("zy persistent Message"));  
         
        //第二组消息   non_persistent消息
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
        producer.send(session.createTextMessage("zy non persistent Message"));  
         
        //提交会话
        session.commit();
        System.out.println("消息发送成功");
        
	}

}
