package com.yc.web.actions;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.bean.LoginUser;
import com.yc.biz.LoginUserBiz;
import com.yc.biz.SendMessage;
import com.yc.web.model.JsonModel;

@Controller
@Scope(value="prototype")


@Namespace("/")
@ParentPackage("struts-default")
public class LoginUserAction extends BaseAction implements ModelDriven<LoginUser> {
	private static final long serialVersionUID = -1820817356255509848L;
	
	private LoginUser loginUser;
	//业务层
	private LoginUserBiz loginUserBiz;
	
	private JsonModel jsonModel=new JsonModel();
	
	private SendMessage sendMessage;
	
	@Action(value = "/loginUser_login")
	public void login() throws IOException{
		if( loginUserBiz==null){
			jsonModel.setCode(0);
			jsonModel.setMsg("server internal error");
		}else{
			loginUser=this.loginUserBiz.login(loginUser);
			if( loginUser!=null   ){
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", loginUser);
				
				//TODO: 发出邮件发送消息
				Map<String,String> map=new HashMap<String,String>();
				map.put("email", loginUser.getEmail());
				map.put("uname", loginUser.getUname());
				map.put("time", new Date().toGMTString());
				map.put("body", "login website.");
				this.sendMessage.sendMail(map);
				
				jsonModel.setCode(1);
				loginUser.setPwd(null);//不要将密码返回客户端.不安全
				jsonModel.setObj(loginUser);   //  id,  uname
			}else{
				jsonModel.setCode(0);
				jsonModel.setMsg("invalide username or password");
			}
		}
		super.outJson( jsonModel,  ServletActionContext.getResponse() );
	}

	public LoginUser getModel() {
		loginUser=new LoginUser();
		return loginUser;
	}
	
	@Resource(name="loginUserBizImpl")
	public void setLoginUserBiz(LoginUserBiz loginUserBiz) {
		this.loginUserBiz = loginUserBiz;
	}

	@Resource(name="sendMessageImpl")
	public void setSendMessage(SendMessage sendMessage) {
		this.sendMessage = sendMessage;
	}
	






}
