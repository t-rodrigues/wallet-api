CREATE TABLE users (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  email varchar(100) UNIQUE NOT NULL,
  password varchar(100) NOT NULL
);
