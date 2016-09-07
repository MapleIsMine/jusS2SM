项目有四个，用于学习jms的队列机制. 


建议先看:
 1. testjms1 :基础案例，没有使用任何框架. 
 2. s2sm_login 是一个登录的例子， 登录成功后要求发送一个消息到jms服务( 在这里假设网站的登录用户数很多). 框架: struts2+mybatis+spring+jms+maven+mysql. 
    maileservicejms:是消费端，用于从jms中读取消息，并做出处理.  spring+maven+jms
    这两个例子是一起用的。 
3. 一个银行存取款程序，用于模拟存取款操作后为了安全，给用户发送一份邮件通知。
     技术： struts2+mybatis+spring+maven+jms+ velocity+ java mail. 
     
    因为发邮件比较慢且不可靠，为了不拖垮主系统，所以用了jms. 但没有将消费者部分分开，写在一个程序里了。 