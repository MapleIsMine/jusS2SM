package com.yc.web.actions;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.bean.Account;
import com.yc.biz.AccountBiz;
import com.yc.web.actions.model.AccountModel;
import com.yc.web.actions.model.JsonModel;

//   Controller注解是表示让spring将这个类初始化并托管起来...
@ParentPackage("json-default")   //继承包要改，mo认继承自struts-default,改为json-default,目的是引用  result type="json"
@Controller
                                    // type="json": 以流的方式输出json数据
 									// params={"root","jsonModel"} 将 action中  的jsonModel作为  ognl的root对象返回. 
@Results({ @Result(name = "success", type="json",params={"root","jsonModel"}), @Result(name = "fail", type="json",params={"root","jsonModel"}) })
public class AccountAction extends ActionSupport implements ModelDriven<AccountModel> {

	private AccountBiz accountBiz;

	private static final long serialVersionUID = -1820817356255509848L;

	private AccountModel accountModel = new AccountModel();

	private JsonModel<Account> jsonModel;

	public JsonModel<Account> getJsonModel() {
		return jsonModel;
	}


	public void setJsonModel(JsonModel<Account> jsonModel) {
		this.jsonModel = jsonModel;
	}

	@Action(value = "account_deposite")
	public String deposite() {
		try{
			Account account=accountBiz.deposite(accountModel.getAccountid(), accountModel.getMoney());
			jsonModel=new JsonModel<Account>();
			jsonModel.setCode(1);
			jsonModel.setMsg(null);
			jsonModel.setT(account);
			return "success";
		}catch( Exception e){
			jsonModel=new JsonModel();
			jsonModel.setCode(0);
			jsonModel.setMsg(  e.getMessage() );
			jsonModel.setT(null);
			return "fail";
		}
	}

	@Action(value = "account_withdraw")
	public String withdraw() {
		try{
			Account account=accountBiz.withdraw(accountModel.getAccountid(), accountModel.getMoney());
			jsonModel=new JsonModel<Account>();
			jsonModel.setCode(1);
			jsonModel.setMsg(null);
			jsonModel.setT(account);
			return "success";
		}catch( Exception e){
			jsonModel=new JsonModel();
			jsonModel.setCode(0);
			jsonModel.setMsg(  e.getMessage() );
			jsonModel.setT(null);
			return "fail";
		}
	}

	@Action(value = "account_transfer")
	public String transfer() {
		try{
			Account account=accountBiz.transfer(accountModel.getInAccountId(), accountModel.getAccountid(), accountModel.getMoney());
			jsonModel=new JsonModel<Account>();
			jsonModel.setCode(1);
			jsonModel.setMsg(null);
			jsonModel.setT(account);
			return "success";
		}catch( Exception e){
			jsonModel=new JsonModel();
			jsonModel.setCode(0);
			jsonModel.setMsg(  e.getMessage() );
			jsonModel.setT(null);
			return "fail";
		}
	}

	@Action(value = "account_find")
	public String find() {
		try{
			Account account=accountBiz.findAccount(accountModel.getAccountid());
			jsonModel=new JsonModel<Account>();
			jsonModel.setCode(1);
			jsonModel.setMsg(null);
			jsonModel.setT(account);
			return "success";
		}catch( Exception e){
			jsonModel=new JsonModel();
			jsonModel.setCode(0);
			jsonModel.setMsg(  e.getMessage() );
			jsonModel.setT(null);
			return "fail";
		}
	}

	// spring:
	@Resource(name = "accountBizImpl")
	public void setAccountBiz(AccountBiz accountBiz) {
		this.accountBiz = accountBiz;
	}

	@Override
	public AccountModel getModel() {
		return accountModel;
	}

}
