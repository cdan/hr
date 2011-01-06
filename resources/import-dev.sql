-- SQL statements which are executed at application startup if hibernate.hbm2ddl.auto is 'create' or 'create-drop'

insert into user (id,name ,password,role) values (1, 'admin','admin','admin');
insert into user (id,name ,password,role) values (2, 'user','user','user');