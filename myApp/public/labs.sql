create table labs (
    id INT  PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(20) NOT NULL,
    capacity INT DEFAULT '0',
    lab_type INT DEFAULT '0',
    college_type INT DEFAULT '0',
    x INT DEFAULT '0',
    y INT DEFAULT '0'
);
INSERT INTO labs (id,title, capacity, lab_type, college_type, x, y) VALUES (null,'Lab 1', 10, 1, 1, 0, 0);
INSERT INTO labs (id,title, capacity, lab_type, college_type, x, y) VALUES (null,'Lab 2', 10, 1, 1, 0, 0);
