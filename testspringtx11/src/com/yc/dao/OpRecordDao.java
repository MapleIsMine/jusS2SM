package com.yc.dao;

import java.util.List;

import com.yc.bean.OpRecord;

public interface OpRecordDao {
	public void insertOpRecord( OpRecord opRecord);
	
	/**
	 *  查看当天的历史记录
	 */
	public List<OpRecord> findCurrentDayRecord(  OpRecord opRecord );
}
