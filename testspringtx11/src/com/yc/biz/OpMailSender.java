package com.yc.biz;

import java.io.File;

import com.yc.bean.AccountOperation;

public interface OpMailSender {
	public void sendOpEmail(AccountOperation accountOperation);

	public void sendTodayOpRecordEmail(File file);
}
