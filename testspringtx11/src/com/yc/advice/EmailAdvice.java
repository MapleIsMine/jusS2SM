package com.yc.advice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yc.bean.Account;
import com.yc.bean.AccountEmail;
import com.yc.bean.AccountOperation;
import com.yc.bean.OpType;
import com.yc.biz.OpMailSender;
import com.yc.dao.AccountDao;
import com.yc.dao.AccountEmailDao;
import com.yc.messages.EmailAccountOperationMessageProducerService;

@Component
@Aspect
public class EmailAdvice {
	public static final String TRANSFER = "transfer";
	public static final String WITHDRAW = "withdraw";
	public static final String DEPOSITE = "deposite";

	private AccountEmailDao accountEmailDao;
	private AccountDao accountDao;
	
	private EmailAccountOperationMessageProducerService emailAccountOperationMessageProducerService;
	
	

	@Around("execution(* com.yc.biz.impl.AccountBizImpl.deposite(..)) || execution(* com.yc.biz.impl.AccountBizImpl.withdraw(..))   ||  execution(* com.yc.biz.impl.AccountBizImpl.transfer(..))")
	public Object sendEmailMethod(ProceedingJoinPoint jp) {
		Object obj=null;
		try {
			 obj=jp.proceed();   //执行业务层中的类的真正操作   deposite()
			if(   obj!=null ){
				Account account=(Account)obj;  
				// 1. 取出accountid
				String methodName = jp.getSignature().getName();
				double opmoney=0;
				int inaccountid=0;
				if (TRANSFER.equals(methodName)) {
					opmoney=Double.parseDouble( jp.getArgs()[2].toString() );
					inaccountid=Integer.parseInt(   jp.getArgs()[0].toString());
				} else {
					opmoney=Double.parseDouble( jp.getArgs()[1].toString() );
				}
				
				// 2. 调用dao根据accountid,查询 email
				AccountEmail ae = this.accountEmailDao.findAccountEmail(account.getAccountid());
				String email = ae.getEmail(); // 接收方的email地址.
				AccountOperation ao=new AccountOperation();
				ao.setEmail(email);
				ao.setOpmoney(opmoney);
				DateFormat df=new SimpleDateFormat( "yyyy年MM月dd日 HH:mm:ss");
				ao.setTime(     df.format(new Date()) );
				ao.setAccountid(account.getAccountid());
				ao.setBalance(  account.getBalance()  );
				switch(    methodName){
					case TRANSFER:
						ao.setOptype(     OpType.transfer );
						Account inAccount=this.accountDao.findAccount(inaccountid);
						AccountEmail ae2 = this.accountEmailDao.findAccountEmail(inAccount.getAccountid());
						String email2 = ae2.getEmail(); // 接收方的email地址.
						ao.setInaccountbalance(inAccount.getBalance());
						ao.setInaccountid(inaccountid);
						ao.setInaccountemail(email2);
						break;
					case WITHDRAW:
						ao.setOptype(     OpType.withdraw );
						break;
					case DEPOSITE:
						ao.setOptype(     OpType.deposit );
						break;
				}
				//System.out.println(   ao   );
				// 3. 发送 重新配置 email
				//this.opMailSender.sendOpEmail(ao);
				// TODO:发送消息
				this.emailAccountOperationMessageProducerService.sendMessage(ao);
				
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return obj;

	}

	@Pointcut("execution(* com.yc.biz.impl.AccountBizImpl.deposite(..)) || execution(* com.yc.biz.impl.AccountBizImpl.withdraw(..))   ||  execution(* com.yc.biz.impl.AccountBizImpl.transfer(..))")
	private void doSomething() {
	}

	@Resource(name = "accountEmailDaoMyBatisImpl")
	public void setAccountEmailDao(AccountEmailDao accountEmailDao) {
		this.accountEmailDao = accountEmailDao;
	}
	@Resource(name = "accountDaoMybatisImpl")
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Resource(name="emailAccountOperationMessageProducerServiceImpl")
	public void setEmailAccountOperationMessageProducerService(
			EmailAccountOperationMessageProducerService emailAccountOperationMessageProducerService) {
		this.emailAccountOperationMessageProducerService = emailAccountOperationMessageProducerService;
	}

	
	
	

}
