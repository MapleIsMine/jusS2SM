package com.yc.mailservice.mailservicejms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   
    public AppTest( String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testApp()
    {
    	ApplicationContext ac=new ClassPathXmlApplicationContext("beans*.xml");
    	
    	Thread t=new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){}
			}
    		
    	});
    	t.start();
    	
    }
}
