INSERT INTO USER (user_id, name, email, password, last_login)
    VALUES (1, 'Dhoni', 'dhoni@gmail.com', 'BJdHbUkSp4B1wabxZlQ4J6XgrrNbTXin', {ts '2020-05-13 00:00:00.00'});

INSERT INTO EMPLOYEE (id, name, doj, dept, email)
    VALUES (1, 'Kohli', CURRENT_DATE(), 'Engineering', 'kohli@bcci.com');
INSERT INTO EMPLOYEE (id, name, doj, dept, email)
    VALUES (2, 'Sachin', CURRENT_DATE(), 'Design', 'sachin@bcci.com');