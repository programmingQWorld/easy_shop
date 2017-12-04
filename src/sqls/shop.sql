drop database if exists shop;
/*创建数据库，并设置编码*/
create database shop default character set utf8;

use shop;
/*删除管理员表*/
drop table if exists account;
/*删除商品类别表*/
drop table if exists category;

/*============================*/
/*      Table：管理员表结构                       */
/*============================*/
create table account
(
  /* 管理员编号，自动增长 */
  id int primary key not null auto_increment,
  /* 管理员登录名 */
  login varchar(20),
  /* 管理员姓名 */
  name varchar(20),
  /* 管理员密码 */
  pass varchar(20)
);

/*============================*/
/*     Table：商品类别表结构                      */
/*============================*/
create table category
(
  /* 类别编号，自动增长 */
  id  int primary key not null auto_increment,
  /* 类别名称 */
  type varchar(20),
  /* 类别是否为热点类别，热点类别才有可能显示在首页*/
  hot  bool default false,
  /* 外键，此类别由哪位管理员管理 */
  account_id int,
  constraint aid_FK foreign key(account_id) references account(id)
);

insert into category (type, hot, account_id) values
  ('服装', 1, 1),
  ('电脑', 0, 2),
  ('手机', 0, 2),
  ('手表', 1, 1);

insert into account (login, name, pass) values
  ('222@ali.com', 'lcq', '-lcq'),
  ('333@ali.com', 'Tom', '=lcq'),
  ('444@ali.com', 'John', '-lcq'),
  ('555@ali.com', 'Li', '+lcq'),
  ('666@ali.com', 'Lei', '=lcq');

insert into category (type, hot, account_id) values
  ('测试数据1', 2, 1),
  ('测试数据2', 2, 1),
  ('测试数据3', 2, 1),
  ('测试数据4', 2, 1),
  ('测试数据5', 2, 1),
  ('测试数据6', 2, 1),
  ('测试数据7', 2, 1),
  ('测试数据8', 2, 1),
  ('测试数据9', 2, 1);

/*=============================*/
/* Table: 商品表结构            */
/*=============================*/
create table product
(
  /* 商品编号,自动增长 */
  id                  int primary key not null auto_increment,
  /* 商品名称 */
  name                varchar(20),
  /* 商品价格 */
  price               decimal(8,2),
  /* 商品图片 */
  pic                 varchar(200),
  /* 商品简单介绍 */
  remark              longtext,
  /* 商品详细介绍 */
  xremark             longtext,
  /* 商品生产日期 */
  date                timestamp default CURRENT_TIMESTAMP,
  /* 是否为推荐商品,推荐商品才有可能显示在商城首页 */
  commend             bool,
  /* 是否为有效商品,有效商品才有可能显示在商城首页 */
  open                bool,
  /* 商品所在的类别编号*/
  cid                  int,
  constraint cid_FK foreign key(cid) references category(id)
);

insert into product (name, price, pic, remark, xremark, date, commend, open, cid) values
  ('HUAWEI MateBook D', 4788.00, '', '15.6英寸笔记本电脑(i5 8G 128G+500G 2G独显 极光蓝)', '优惠信息图片, 关联推荐图片,商品测评图片,商品详情图片,售后服务图片','2017/6/01', 1, 1, 28);

insert into product (name, price, pic, remark, xremark, date, commend, open, cid) values
  ('华为(HUAWEI) M3', 1989.00, '', '青春版 8.0英寸 平板电脑(哈曼卡顿音效 4G内存/64G存储 全网通) 流光金', '都是图片', '2017/5/31', 1, 1, 28);

insert into product (name, price, pic, remark, xremark, date, commend, open, cid) values
  ('意尔康', 199.00, '', 'xxxxxxxxxxx', 'eeeeeeeeeeeeeee', '2017-4-16', 1, 1, null);

insert into product (name, price, pic, remark, xremark, date, commend, open, cid) values
  ('红蜻蜓', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null);

insert into product (name, price, pic, remark, xremark, date, commend, open, cid) values
  ('测试数据1', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据2', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据3', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据4', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据5', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据6', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据7', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null),
  ('测试数据8', 199.00, '', 'eeeeeeeeeeeeeee', 'xxxxxxxxxxx', '2017-4-16', 1, 1, null);


create table sorder (
  id int PRIMARY KEY  NOT NULL  AUTO_INCREMENT,
  name VARCHAR(20),
  price decimal(8,2),
  number int not  null,
  pid int,
  fid int,
  foreign key (pid) references product(id),
  foreign key (fid) references forder(id)
) engine = 'innodb' default charset = 'utf8';

select * from product left join category on cid = category.id where type like '%';

#查询通过种类id作为联系的商品携带商品种类信息，并且只要可推荐，可购买的商品
select * from product left join category on cid = category.id where commend = 1 and open = 1 and cid = 27;

select name, commend, open, cid from product;


insert into user (login, name, pass, sex, phone, email)
values ( 'lin-cq', 'lcq', 'opopop', '男', '13172657669', 'aawoshilcq@163.com');
insert into user (login, name, pass, sex, phone, email)
values ( 'lliu-cee', 'le', 'opopop', '女', '13172659369', 'liuce@163.com');