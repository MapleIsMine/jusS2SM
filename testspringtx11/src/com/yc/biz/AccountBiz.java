package com.yc.biz;

import com.yc.bean.Account;

public interface AccountBiz {

	public Account deposite(int accountid, double money);

	public Account withdraw(int accountid, double money);

	public Account transfer(int inAccountId, int outAccountId, double money);
	
	public Account findAccount( int accountId);
}
