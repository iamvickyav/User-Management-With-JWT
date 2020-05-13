package com.iamvickyav.usermanagement.repo

import com.iamvickyav.usermanagement.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email)
}
