package com.yc.biz.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yc.bean.LoginUser;
import com.yc.biz.LoginUserBiz;
import com.yc.dao.LoginUserDao;
import com.yc.utils.Encrypt;

@Service
@Transactional(readOnly=true)
public class LoginUserBizImpl implements LoginUserBiz {
	private LoginUserDao loginUserDao;
	

	public LoginUser login(LoginUser loginUser)  {
		loginUser.setPwd(  Encrypt.md5(loginUser.getPwd()) );   //md5
		return this.loginUserDao.findAccount(loginUser);
	}

	@Resource( name="loginUserDaoMybatisImpl")
	public void setLoginUserDao(LoginUserDao loginUserDao) {
		this.loginUserDao = loginUserDao;
	}




	
	

}
