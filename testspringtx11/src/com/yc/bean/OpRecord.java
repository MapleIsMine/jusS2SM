package com.yc.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OpRecord {
	private Integer id;
	private Integer accountid;
	private Double opmoney;
	private String optime;

	private String toDayStart;
	private String toDayEnd;

	/**
	 * 返回当天的时间起点: 如   2016-01-01 00:00:00
	 * @return
	 */
	public String getToDayStart() {
		SimpleDateFormat startsdf=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		toDayStart= startsdf.format(new Date() );
		return toDayStart;
	}

	public void setToDayStart(String toDayStart) {
		this.toDayStart = toDayStart;
	}

	/**
	 * 返回当天的时间终点: 如   2016-01-01 23:59:59
	 * @return
	 */
	public String getToDayEnd() {
		SimpleDateFormat endsdf=new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		toDayEnd=endsdf.format(new Date() );
		return toDayEnd;
	}

	public void setToDayEnd(String toDayEnd) {
		this.toDayEnd = toDayEnd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getOptime() {
		return optime;
	}

	public void setOptime(String optime) {
		this.optime = optime;
	}

}
