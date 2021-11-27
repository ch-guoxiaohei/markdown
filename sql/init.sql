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

insert into markdowneditor.t_category(c_id,c_name,dt_create_time,dt_update_time) values('83b2416c24414eb68098a9fae8fabcf2','java',now(),now());

