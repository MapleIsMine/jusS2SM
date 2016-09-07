package com.yc.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.AccountEmail;
import com.yc.dao.AccountEmailDao;

public class TestEmail {
	ApplicationContext ac;
	@Before
	public void setUp(){
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test1() {
		AccountEmailDao aed=(AccountEmailDao) ac.getBean("accountEmailDaoMyBatisImpl");
		
		AccountEmail ae=aed.findAccountEmail(1);
		System.out.println(ae);
	}

}
