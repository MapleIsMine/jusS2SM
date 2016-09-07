package com.yc.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ReplyMessageListener implements MessageListener {
	
	

	//当jms中间件中的队列有消息时，这个消息中间会发送一个信息到本应用，激活本应用中的  这个监听器.回调onMessage
	public void onMessage(Message m) {
		TextMessage tm=(TextMessage)m;
		try {
			//TODO: 通过这一代码，表示消费成功运行，
			//真实项目中，这里考虑修改数据状态( 数据有可能存在数据库，或日志  )
			System.out.println(  tm.getText( )  );
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}

}
