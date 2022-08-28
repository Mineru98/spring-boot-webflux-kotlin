drop table if exists User;
create table User (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   account varchar(512),
   password varchar(512),
   username varchar(512),
   userImageUrl varchar(512),
   email varchar(512),
   introduceDescription varchar(512),
   loginProvider varchar(512) DEFAULT '로컬',
   userRole varchar(512) DEFAULT '일반사용자',
   userStatus varchar(512) DEFAULT '활성',
   primary key (id)
);