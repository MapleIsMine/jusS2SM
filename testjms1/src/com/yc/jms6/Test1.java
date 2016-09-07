package com.yc.jms6;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

//测试生产者发送了一个消息，消费者接收

public class Test1 {

	// 如果找不到对象类，请参考http://activemq.2283324.n4.nabble.com/CONF-Apache-ActiveMQ-gt-ObjectMessage-td4704772.html
	public static void main(String[] args) {
		//System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
		AccountOperation ao = new AccountOperation();
		ao.setEmail("123@163.com");
		ao.setAccountid(1);
		ao.setBalance(2000.0);
		ao.setOpmoney(2000.0);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext2.xml");
		EmailAccountOperationService eaos = (EmailAccountOperationService) ac
				.getBean("emailAccountOperationServiceImpl");
		eaos.sendMessage(ao);
	}

}
