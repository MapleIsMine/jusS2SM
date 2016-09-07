package com.yc.bean;

public class AccountEmail {
	private Integer accountid;
	private String email;

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "AccountEmail [accountid=" + accountid + ", email=" + email + "]";
	}

}
