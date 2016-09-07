package com.yc.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.LoginUser;
import com.yc.dao.LoginUserDao;


@Repository
public class LoginUserDaoMybatisImpl implements LoginUserDao {
	
	private LoginUserDao loginUserMapper;

	public LoginUser findAccount(LoginUser loginUser) {
		return this.loginUserMapper.findAccount(loginUser);
	}

	@Resource( name="loginUserMapper")
	public void setLoginUserMapper(LoginUserDao loginUserMapper) {
		this.loginUserMapper = loginUserMapper;
	}
	
	
	
	

	
	

}
