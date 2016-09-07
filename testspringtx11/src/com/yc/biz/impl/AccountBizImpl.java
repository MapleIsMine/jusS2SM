package com.yc.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.biz.AccountBiz;
import com.yc.dao.AccountDao;
import com.yc.dao.AccountEmailDao;
import com.yc.dao.OpRecordDao;
import com.yc.dao.impl.AccountEmailDaoMybatisImpl;

@Service("accountBizImpl")
public class AccountBizImpl implements AccountBiz {
	private AccountDao accountDao;
	private OpRecordDao opRecordDao;

	@Override
	public Account findAccount(int accountId) {
		Account account= accountDao.findAccount(accountId);
		 
		 return account;
	}

	@Override // 加入事务
	public Account deposite(int accountid, double money) {
		Account account = accountDao.findAccount(accountid);
		if (account == null) {
			throw new RuntimeException("查无此账hu..."); // 为什么要抛出
														// RuntimeException();
		}
		account.setBalance(money);
		accountDao.updateAccount(account);

		OpRecord opRecord = new OpRecord();
		opRecord.setAccountid(accountid);
		opRecord.setOpmoney(money);
		opRecordDao.insertOpRecord(opRecord);
		
		return findAccount( accountid);
	}

	@Override
	public Account withdraw(int accountid, double money) {
		Account account = accountDao.findAccount(accountid);
		if (account == null) {
			throw new RuntimeException("查无此账hu..."); // 为什么要抛出
														// RuntimeException();
		}
		if (account.getBalance() <= money) {
			throw new RuntimeException("余额不足...");
		}
		account.setAccountid(accountid);
		account.setBalance(-money); // 负数表示取
		accountDao.updateAccount(account);

		OpRecord opRecord = new OpRecord();
		opRecord.setAccountid(accountid);
		opRecord.setOpmoney(-money);
		opRecordDao.insertOpRecord(opRecord);
		
		return findAccount( accountid);
	}

	@Override // 事务传播行为，因为在这里 transfer一个事务, deposite 一个事务, withdraw一个事务
	public Account transfer(int inAccountId, int outAccountId, double money) {
		deposite(inAccountId, money);
		withdraw(outAccountId, money);
		return findAccount( outAccountId);
	}

	@Resource(name = "accountDaoMybatisImpl")
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Resource(name = "opRecordDaoMyBatisImpl")
	public void setOpRecordDao(OpRecordDao opRecordDao) {
		this.opRecordDao = opRecordDao;
	}

}
