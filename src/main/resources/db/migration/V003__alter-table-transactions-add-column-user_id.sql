ALTER TABLE transactions ADD COLUMN user_id bigint NOT NULL;
ALTER TABLE transactions ADD FOREIGN KEY (user_id) REFERENCES users (id);
