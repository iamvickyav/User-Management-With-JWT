# User-Management-With-JWT

This is a simple REST application to demonstrate a User (Employee) management system. 

**Technologies/frameworks used** - Groovy, SpringBoot, Jwt, Logback H2 Database

## How to test

In order for the applicaiton to run, you need to have Java installed in your system

```
> ./mvnw clean install

> ./mvnw spring-boot:run

```

Once application started, use the two postman collections available inside **postman_collection** folder in root of this project to test

## Application Flows

We have an admin user, who can

- Login
- Change his password

**For doing above operation, jwt is not needed**

Admin user also can

- Add Employee
- Delete Employee
- Update Employee
- View All Employees
- View one particular Employee

**For doing any of the above operations, admin has to provide JWT token in the Authorization header**

## Unsecured Endpoints

### Login Flow 

[ POST Endpoint with PATH - /login ]

Admin user has to login to get a valid JWT. You can find the email & password for admin user in data.sql file inside src/main/resources

---

### Change Password Flow 

[POST Endpoint with PATH - /password/recreate ]

Admin user can change his password by hitting this endpoint. But for the password reset to be successful he has to send "tokenFromEmail" value (which is assumed to be shared via Email). For now the value is hardcoded & it is **12345**

---

## Secured Endpoints

### Get All Employees

[GET with PATH - /secure/employees with valid JWT Token in Authorization header ]

To get all employees as List

---

### Get one Employee

[GET with PATH - /employee/{employeeId} with valid JWT Token in Authorization header ]

To get details of a particular employee

---


### Delete one Employee

[DELETE method with PATH - /employee/{employeeId} with valid JWT Token in Authorization header ]

To get details of a particular employee

---


### Create new Employee

[POST method with PATH - /employee/ with valid JWT Token in Authorization header ]

To create a new Employee. 
name, dept, email are mandatory fields

---


### Update existing Employee

[PUT method with PATH - /employee/ with valid JWT Token in Authorization header ]

To update dept of existing Employee. 
id, dept are mandatory fields
