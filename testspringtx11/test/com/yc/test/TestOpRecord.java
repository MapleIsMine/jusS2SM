package com.yc.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.OpRecord;
import com.yc.dao.OpRecordDao;

import junit.framework.Assert;

public class TestOpRecord {
	
	ApplicationContext ac;
	@Before
	public void setUp(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	}


	@Test
	public void testFindOpRecord() {
		OpRecord opRecord=new OpRecord();
		OpRecordDao ord=(OpRecordDao) ac.getBean("opRecordDaoMyBatisImpl");
		List<OpRecord> list=ord.findCurrentDayRecord(opRecord);
		Assert.assertNotNull(list);
	}
	
	@Test
	public void testFindOpRecord2() {
		Properties p=System.getProperties();
		
		Set keys=p.keySet();
		for(   Object obj: keys){
			String key=(String)obj;
			String value=p.getProperty(key);
			System.out.println(   key+"\t"+value);
		}
	}

}
