CREATE TABLE IF NOT EXISTS `column` (
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `column_card` (
    `column` BIGINT,
    card BIGINT,
    column_key BIGINT
);

CREATE TABLE IF NOT EXISTS card (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    title varchar(50),
    body  varchar(500)
);

CREATE TABLE IF NOT EXISTS `user` (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR (20),
    password VARCHAR (20)
);

CREATE TABLE IF NOT EXISTS activity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    `action` CHAR(10),
    title VARCHAR(50),
    `from` VARCHAR(50),
    `to` VARCHAR(50),
    action_time TIMESTAMP
);
