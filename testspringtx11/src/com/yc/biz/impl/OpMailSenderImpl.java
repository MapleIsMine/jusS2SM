package com.yc.biz.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.yc.bean.AccountOperation;
import com.yc.biz.OpMailSender;
import com.yc.bean.OpType;

@Service("opMailSenderImpl")
public class OpMailSenderImpl implements OpMailSender {

	private JavaMailSender mailSender; // 邮件发送的帮助类
	private VelocityEngine velocityEngine; // 模板解析引擎

	@Resource(name = "mailSender")
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Resource(name = "velocityEngine")
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	
	public void sendTodayOpRecordEmail(   File file){
		try {
			MimeMessage message = mailSender.createMimeMessage();
			// use the true flag to indicate you need a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true,"utf-8");
			helper.setTo("zhangyingchengqi@163.com");
			helper.setFrom("zhangyingchengqi@163.com");
			helper.setSubject("银行日流水清单");
			helper.setText("银行日流水清单");
			FileSystemResource f = new FileSystemResource( file);
			helper.addAttachment(f.getFilename(), f);

			mailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendOpEmail(final AccountOperation accountOperation) {
		switch (accountOperation.getOptype()) {
		case deposit:
		case withdraw:
			MimeMessagePreparator preparator = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"utf-8");
									message.setSubject("对账单");
									message.setTo(accountOperation.getEmail());    //接收方邮箱
									message.setFrom("zhangyingchengqi@163.com"); //发送方邮箱
									
									//放到模板中的参数
									Map model = new HashMap();
									model.put("accountOperation", accountOperation);
									String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
											"com/yc/web/view/vm/depositewithdraw.vm","utf-8" ,model);
									message.setText(text, true);
				}
			};
			this.mailSender.send(preparator);
			break;
		case transfer:
			MimeMessagePreparator preparator1 = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage ,"utf-8");
									message.setSubject("对账单");
									message.setTo(accountOperation.getEmail());    //接收方邮箱
									message.setFrom("zhangyingchengqi@163.com"); //发送方邮箱
									//放到模板中的参数
									Map model = new HashMap();
									model.put("accountOperation", accountOperation);
									String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
											"com/yc/web/view/vm/transferout.vm","utf-8", model);
									message.setText(text, true);
				}
			};
			this.mailSender.send(preparator1);
			
			MimeMessagePreparator preparator2 = new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws Exception {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"utf-8");
									message.setSubject("对账单");
									message.setTo(accountOperation.getInaccountemail());    //接收方邮箱
									message.setFrom("zhangyingchengqi@163.com"); //发送方邮箱
									//放到模板中的参数
									Map model = new HashMap();
									model.put("accountOperation", accountOperation);
									String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
											"com/yc/web/view/vm/transferin.vm","utf-8", model);
									message.setText(text, true);
				}
			};
			this.mailSender.send(preparator2);
			break;
		}
	
}

}
