package com.yc.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yc.bean.LoginUser;

// Dao�ӿ�,   Mapper
public interface LoginUserDao {
	
	
	 @Select("select * from loginuser where uname=#{uname} and pwd=#{pwd}")
	public LoginUser findAccount(  LoginUser loginUser);
}
