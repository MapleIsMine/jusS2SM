package com.yc.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.AccountEmail;
import com.yc.dao.AccountEmailDao;

@Repository("accountEmailDaoMyBatisImpl")
public class AccountEmailDaoMybatisImpl implements AccountEmailDao {
	private SqlSessionTemplate sqlSession;
	
	@Override
	public AccountEmail findAccountEmail(Integer accountid) {
		 return sqlSession.selectOne("com.yc.dao.accountEmailMapper.findAccountEmail", accountid);
	}
	
	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
