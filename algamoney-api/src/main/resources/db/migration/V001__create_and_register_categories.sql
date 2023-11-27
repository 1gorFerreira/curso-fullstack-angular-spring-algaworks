CREATE TABLE category (
    `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) VALUES ('Leisure');
INSERT INTO category (name) VALUES ('Food');
INSERT INTO category (name) VALUES ('Supermarket');
INSERT INTO category (name) VALUES ('Pharmacy');
INSERT INTO category (name) VALUES ('Others');
