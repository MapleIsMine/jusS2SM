<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>

<script type="text/javascript">
	function doSubmit(   url){
		$.ajax(
				{
					type : "POST",
					url : url,
					data : $("#myform").serialize(),
					dataType : "json",
					success : function(data) { 
						if(  data.code==1){
							$("#result").html("<font size=16>操作成功,当前账户: "+   data.t.accountid+"&nbsp;&nbsp;"+ data.t.balance+"</font>");
						}else{
							$("#result").html("<font size=16>操作失败,错误信息:"+data.msg+"</font>");	
						}
					}
				}
		);
	}

	function deposite() {
		doSubmit( "account_deposite" );
	}
	function withdraw() {
		doSubmit( "account_withdraw" );
	}
	function transfer() {
		doSubmit( "account_transfer" );
	}
</script>

</head>
<body>

	<form id="myform" action="" method="post">
	账hu:
	<input type="text" name="accountid" />
	<br /> 金额:
	<input type="text" name="money" />
	<br /> 接收账hu:
	<input type="text" name="inAccountId" />
	<br />

		<input type="button" value="存款"  onclick="deposite()" />
		<input type="button" value="取款" onclick="withdraw()" />
		<input type="button" value="转账" onclick="transfer()" />
	</form>

	<div id="result"></div>

</body>
</html>