package com.yc.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.LoginUser;
import com.yc.biz.LoginUserBiz;

import junit.framework.TestCase;

public class TestMybatis extends TestCase {

	
	
	@Test  // 业务层测试成功
	public void test1(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("beans_mybatis.xml");
		
		LoginUser lu=new LoginUser();
		lu.setUname("a");
		lu.setPwd("a");
		
		LoginUserBiz lub=(LoginUserBiz) ac.getBean("loginUserBizImpl");
		lu=lub.login(lu);
		
		System.out.println( lu!=null );
		System.out.println( lu.getId());
	}
	
	
}
