package com.yc.bean;

import java.io.Serializable;

public class LoginUser implements Serializable {
	private Integer id;
	private String uname;
	private String pwd;
	private String email;
	
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", uname=" + uname + ", pwd=" + pwd
				+ "]";
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	

}
