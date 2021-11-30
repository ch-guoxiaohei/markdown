-- create markdown-editor
drop schema if exists markdowneditor;
create schema markdowneditor;

drop table if exists markdowneditor.t_post;

create table markdowneditor.t_post
(
  c_id varchar (32) not null,
  c_title varchar(300) not null,
  c_overview varchar (300) not null,
  c_content TEXT ,
  c_origin_content TEXT,
  c_category_id varchar(32),
  c_user_id varchar(300) ,
  dt_create_time timestamp ,
  dt_update_time timestamp
);

drop table if exists markdowneditor.t_category;

create table markdowneditor.t_category
(
  c_id varchar(32) not null,
  c_name varchar(300) not null,
  dt_create_time timestamp ,
  dt_update_time timestamp
);

insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf0','Shell',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf1','Operate System',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf2','Java',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf3','English',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf4','Python',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf5','Micro Service',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf6','Test',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf7','Big Data',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf8','Life',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf9','Frontend',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabc11','Geek',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabc12','Algorithm',now(),now());
insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabc13','Design',now(),now());