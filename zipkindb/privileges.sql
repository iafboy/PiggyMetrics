use mysql;
select host, user from user;
drop user if EXISTS zipkinuser;
flush privileges;
create user zipkinuser identified by 'Welcome1';
flush privileges;
grant all on zipkindatabase.* to zipkinuser@'%' identified by 'Welcome1' with grant option;
flush privileges;
