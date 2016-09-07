package com.yc.jms5;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

//测试生产者发送了一个消息，消费者接收后，再回复一个信息到生产者，生产者接收到后，显示这个回复的信息
public class Test2 {

	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonService ps=(PersonService) ac.getBean("personServiceImpl2");
		//for( int i=0;i<100;i++){
			ps.sendMessage("我是生产者2生产的消息: hello world");
		//}
	}

}
