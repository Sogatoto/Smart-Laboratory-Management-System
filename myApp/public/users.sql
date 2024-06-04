create table users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    roleId INT NOT NULL,
    is_default INT DEFAULT '0',
    FOREIGN KEY (roleId) REFERENCES roles(id)
);

INSERT INTO  users (username, password,roleId,is_default) VALUES ('admin', '123456',1,1);
INSERT INTO users (username, password,roleId) VALUES ('SeaCode', '123123',2);