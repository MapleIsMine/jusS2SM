package com.yc.bean;

import java.io.Serializable;

public class AccountOperation implements Serializable{
	private static final long serialVersionUID = -4101316586036090833L;
	private Integer accountid;
	private Double opmoney;
	private Double balance;
	private String time;
	private OpType optype;
	private String email;
	
	
	
	
	private Integer inaccountid;
	private Double inaccountbalance;
	private String inaccountemail;
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInaccountemail() {
		return inaccountemail;
	}

	public void setInaccountemail(String inaccountemail) {
		this.inaccountemail = inaccountemail;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public Double getOpmoney() {
		return opmoney;
	}

	public void setOpmoney(Double opmoney) {
		this.opmoney = opmoney;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public OpType getOptype() {
		return optype;
	}

	public void setOptype(OpType optype) {
		this.optype = optype;
	}

	public Integer getInaccountid() {
		return inaccountid;
	}

	public void setInaccountid(Integer inaccountid) {
		this.inaccountid = inaccountid;
	}

	public Double getInaccountbalance() {
		return inaccountbalance;
	}

	public void setInaccountbalance(Double inaccountbalance) {
		this.inaccountbalance = inaccountbalance;
	}

	@Override
	public String toString() {
		return "AccountOperation [accountid=" + accountid + ", opmoney=" + opmoney + ", balance=" + balance + ", time="
				+ time + ", optype=" + optype + ", email=" + email + ", inaccountid=" + inaccountid
				+ ", inaccountbalance=" + inaccountbalance + ", inaccountemail=" + inaccountemail + "]";
	}

	

}
