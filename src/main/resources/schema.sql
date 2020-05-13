CREATE TABLE USER
        (
        user_id NUMBER(10) NOT NULL AUTO_INCREMENT,
        name VARCHAR2(50) NOT NULL,
        email VARCHAR2(50) NOT NULL,
        password VARCHAR2(50) NOT NULL,
        last_login TIMESTAMP NOT NULL,
        PRIMARY KEY(user_id)
        );
CREATE TABLE EMPLOYEE
        (
        id NUMBER(10) NOT NULL AUTO_INCREMENT,
        name VARCHAR2(50) NOT NULL,
        doj DATE NOT NULL,
        dept VARCHAR2(50) NOT NULL,
        email VARCHAR2(50) NOT NULL,
        PRIMARY KEY(id)
        );