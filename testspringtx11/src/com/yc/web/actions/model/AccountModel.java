package com.yc.web.actions.model;

public class AccountModel {
	private int accountid;
	private double money;
	private int inAccountId;

	public int getInAccountId() {
		return inAccountId;
	}

	public void setInAccountId(int inAccountId) {
		this.inAccountId = inAccountId;
	}

	public int getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

}
