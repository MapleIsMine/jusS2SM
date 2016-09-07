-- auto_increment去掉
create table account
(
   accountid int primary key ,
   balance   numeric(10,2)
);
--  时间和日期类型  date
create table oprecord
(
   id int primary key ,
   accountid int,
   opmoney numeric(10,2),
   optime date
);

alter table oprecord 
  add constraint fk_oprecord_accountid
     foreign key(accountid) references account(accountid);
     
alter table account
   add constraint ck_account_balance
   check(balance>=0);
 --用序列生成主键  
 create sequence seq_account;
 create sequence seq_oprecord;
   
 insert into account(accountid,balance) values(seq_account.nextval,100);
 insert into account(accountid,balance) values(seq_account.nextval,1000);
 
 --oracle对约束起作用,  抛出异常 ->  mybatis  (dao层的注解  @Repository-> 将exception转换异常RuntimeException )  -> biz   
 --   ->  spring做事务管理 -> spring的事务管理会bu捉到异常 (  RuntimeException起作用 ) -> 可以自动完成事务 rollback()
 insert into account(accountid,balance) values(seq_account.nextval,-10);
 
 commit;


select * from account;
select * from oprecord;

----------------------增加邮箱表
create table accountEmail(
       accountid int primary key ,
       email varchar2(500)
)
insert into accountEmail values( 1,'249652600@qq.com');
insert into accountEmail values(2,'249652600@qq.com');

commit;