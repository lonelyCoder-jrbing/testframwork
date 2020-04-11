drop table if  exists user;
create table user (
        id int(10) not null AUTO_INCREMENT,
        user_name varchar (30) not null,
        password varchar (30) not null ,
        patternLock varchar (40) not null ,
        primary key (id)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 创建普通索引
ALTER TABLE user ADD INDEX 'IDX_USER_CT' (id) USING BTREE;

-- PRIMARY KEY（主键索引）
-- ALTER TABLE `table_name` ADD PRIMARY KEY ( `column` )
-- UNIQUE(唯一索引)
-- ALTER TABLE `table_name` ADD UNIQUE (`column`)
-- INDEX(普通索引)
-- mysql>ALTER TABLE `table_name` ADD INDEX index_name ( `column` )
-- FULLTEXT(全文索引)
-- ALTER TABLE `table_name` ADD FULLTEXT ( `col


drop table  if exists student;
create table  student(
id int (10) not null AUTO_INCREMENT,
code varchar (20) not null ,
name varchar (10) not null ,
age int (10) not null ,
phoneNo varchar (20) not null ,
 primary key (id)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


drop table  if exists course;
create table  course(
course_id int (10) not null AUTO_INCREMENT,
studnet_id int (10) not null,
name varchar (10) not null ,
teacher varchar (10) not null ,
constraint classes_fk foreign key(studnet_id) references student(id),
primary key (course_id)
)
ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;