package com.yc.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ReplyMessageListener implements MessageListener {
	
	

	//��jms�м���еĶ�������Ϣʱ�������Ϣ�м�ᷢ��һ����Ϣ����Ӧ�ã����Ӧ���е�  ���������.�ص�onMessage
	public void onMessage(Message m) {
		TextMessage tm=(TextMessage)m;
		try {
			//TODO: ͨ����һ���룬��ʾ���ѳɹ����У�
			//��ʵ��Ŀ�У����￼���޸�����״̬( �����п��ܴ������ݿ⣬����־  )
			System.out.println(  tm.getText( )  );
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		
	}

}
