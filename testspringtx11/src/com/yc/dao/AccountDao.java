package com.yc.dao;

import com.yc.bean.Account;

public interface AccountDao {

	public void updateAccount( Account account);
	
	public Account findAccount(  int accountid);
}
