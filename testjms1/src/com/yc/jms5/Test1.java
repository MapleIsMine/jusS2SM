package com.yc.jms5;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

//测试生产者发送了一个消息，消费者接收

//整合spring    http://haohaoxuexi.iteye.com/blog/1893038
public class Test1 {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonService ps=(PersonService) ac.getBean("personServiceImpl");
		for( int i=0;i<100;i++){
			ps.sendMessage("hello world");
		}
	}

}
