package com.yc.dao.impl;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.Account;
import com.yc.dao.AccountDao;

@Repository("accountDaoMybatisImpl")
public class AccountDaoMybatisImpl implements AccountDao {
	private SqlSessionTemplate sqlSession;

	@Override
	public void updateAccount(Account account) {
		// mapper命名空间+ 对应的方法
		// 解析 mapper的 xml => update account set balance=balance+#{balance} where
		// accountid=#{accountid}

		sqlSession.update("com.yc.dao.accountMapper.updateAccount", account);

		// sqlSession.update( 注解类.class, account);

		// =>
		// Connection con= sqlsession.getConnection();
		// PreparedStatement pstmt= con.preparedStatement( "update account set
		// balance=balance+? where accountid=?");
		// for(int i=0;i<xxx.size();i++){
		// pstmt.setXXX( i+1, xxx.get(i);
		// }
		// pstmt.executeUpdate( );

	}

	@Override
	public Account findAccount(int accountid) {
		return sqlSession.selectOne("com.yc.dao.accountMapper.findAccount", accountid);
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

}
