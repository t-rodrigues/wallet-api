CREATE TABLE transactions (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  ticker varchar(6) NOT NULL,
  price decimal(18,2) NOT NULL,
  quantity int NOT NULL,
  type varchar(100) NOT NULL,
  date date NOT NULL
);
