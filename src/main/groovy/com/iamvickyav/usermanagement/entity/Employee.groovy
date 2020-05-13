package com.iamvickyav.usermanagement.entity

import com.fasterxml.jackson.annotation.JsonFormat

import javax.persistence.*

@Entity
@Table(name = "EMPLOYEE")
class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Column(name = "name")
    String name

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    @Column(name = "doj")
    Date doj

    @Column(name = "dept")
    String dept

    @Column(name = "email")
    String email
}
