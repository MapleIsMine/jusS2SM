create table loginuser(
	id int primary key auto_increment,
	uname varchar(50),
	pwd varchar(50),
	email varchar(200)
);

insert into loginuser(uname, pwd, email) values( 'a','0cc175b9c0f1b6a831c399e269772661','1069595532@qq.com');