package com.yc.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.yc.bean.OpRecord;
import com.yc.dao.OpRecordDao;

@Repository("opRecordDaoMyBatisImpl")
public class OpRecordDaoMybatisImpl implements OpRecordDao {
	private SqlSessionTemplate sqlSession;

	@Override
	public void insertOpRecord(OpRecord opRecord) {
		this.sqlSession.insert("com.yc.dao.opRecordMapper.insertOpRecord", opRecord);
	}
	
	@Override
	public List<OpRecord> findCurrentDayRecord(OpRecord opRecord) {
		List<OpRecord> list = this.sqlSession.selectList("com.yc.dao.opRecordMapper.findCurrentDayRecord", opRecord);
		return list;
	}

	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	
}
