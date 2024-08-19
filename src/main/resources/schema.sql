CREATE TABLE IF NOT EXISTS `tb_users` (
  id bigint AUTO_INCREMENT primary key,
  name varchar(255) not null,
  username varchar(255) not null,
  password varchar(255) not null,
  email varchar(255) not null
);