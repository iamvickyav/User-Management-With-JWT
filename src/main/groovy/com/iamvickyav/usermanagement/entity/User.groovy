package com.iamvickyav.usermanagement.entity

import javax.persistence.*

@Entity
@Table(name = "USER")
class User {

    @Id
    @Column(name = "userId")
    Integer userId

    @Column(name = "name")
    String name

    @Column(name = "password")
    String password

    @Column(name = "email")
    String email

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login")
    Date lastLogin
}
