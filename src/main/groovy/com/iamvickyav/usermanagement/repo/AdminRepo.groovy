package com.iamvickyav.usermanagement.repo

import com.iamvickyav.usermanagement.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

import javax.transaction.Transactional

interface AdminRepo extends JpaRepository<User, Integer> {

    User findByEmail(String email)

    @Modifying
    @Transactional
    @Query(value = "UPDATE User user SET user.password =:encryptedPassword WHERE user.userId  =:id")
    void updateUserPassword(@Param("encryptedPassword") String encryptedPassword, @Param("id") Integer id)
}
