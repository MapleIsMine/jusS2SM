package com.yc.messages;

import com.yc.bean.AccountOperation;

public interface EmailAccountOperationMessageProducerService {
	
	public void sendMessage(  AccountOperation accountOperation );
}
