
drop database dinemore;

create database dinemore;

use dinemore;


create table employee(
	id varchar(5),
	name varchar(40),
	position char,
	username varchar(40),
	password varchar(40),
	Constraint primary key(id)
);

create table customer(
	id int auto_increment,
	name varchar(40),
	contact varchar(10),
	Constraint primary key (id)
);

create table orders(
	id int auto_increment,
	qty int,
	processTime time,
	TOno varchar(5),
	Chefno varchar(5),
	Constraint primary key (id),
	Constraint foreign key (TOno) REFERENCES employee(id) ON DELETE CASCADE ON Update CASCADE,
	Constraint foreign key (Chefno) REFERENCES employee(id) ON DELETE CASCADE ON Update CASCADE
);


create table orderdetail(
	id int auto_increment,
	cid int,
	oid int,
	date date,
	state boolean,
	Constraint primary key (id),
	Constraint foreign key (cid) REFERENCES customer(id) ON DELETE CASCADE ON Update CASCADE,
	Constraint foreign key (oid) REFERENCES orders(id) ON DELETE CASCADE ON Update CASCADE
);


insert into employee values('MG001','ManagerName','M','bWFuYWdlcg==','aWpzZQ==');
	MO001 | ManagerName | M        | bWFuYWdlcg== | aWpzZQ== |
insert into employee values('CF001',' Nihal','C','niha','1234');
insert into employee values('CF002',' Suresh','C','sura','ijse');
insert into employee values('TO001',' Saman','T','sama','1234');
insert into employee values('TO002',' Nayana','T','nayana','ijse');


	insert


select * from employee e,orderdetail od, order o where od.oid=o.id & o.Chefno=e.id & e.id='CF001';


select SUM(processTime),COUNT(ID),SUM(QTY) FROM ORDERS WHERE CHEFNO='CF001';


SELECT e.name,SUM(o.processTime),COUNT(o.id),sum(o.qty) FROM EMPLOYEE E, ORDERS O WHERE E.ID='CF001';

 select * from order o, employee e where o.chefno=e.id;

  select count(o.id),sum(o.qty),sum(processTime) from order o, employee e where o.chefno=e.id;

  select count(o.id),sum(o.qty),sum(processTime),e.name,e.id from order o, employee e where o.chefno=e.id && e.id='CF001';

 