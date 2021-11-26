-- create markdown-editor
drop schema if exists markdowneditor;
create schema markdowneditor;

drop table if exists t_post;

create table markdowneditor.t_post
(
  c_id varchar (32) not null,
  c_title varchar(300) not null,
  c_content TEXT ,
  c_origin_content TEXT,
  c_user_id varchar(300) ,
  dt_create_time timestamp ,
  dt_update_time timestamp
);
