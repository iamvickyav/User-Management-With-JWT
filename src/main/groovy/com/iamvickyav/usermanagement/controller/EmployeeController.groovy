package com.iamvickyav.usermanagement.controller

import com.iamvickyav.usermanagement.entity.Employee
import com.iamvickyav.usermanagement.model.CreateEmployeeRequest
import com.iamvickyav.usermanagement.model.EmployeeUpdateRequest
import com.iamvickyav.usermanagement.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@RestController
@RequestMapping("/secure")
class EmployeeController {

    @Autowired
    EmployeeService employeeService

    @GetMapping("/employee/{employeeId}")
    Employee getEmployee(@PathVariable("employeeId") Integer employeeId) {
        employeeService.getEmployeeData(employeeId)
    }

    @PostMapping("/employee")
    Map createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest) {
        Integer employeeId = employeeService.createEmployee(createEmployeeRequest)
        ["message" : "Employee created successfully"]
    }

    @PutMapping("/employee")
    Map updateEmployee(@Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(employeeUpdateRequest)
        ["message" : "Employee updated successfully"]
    }

    @GetMapping("/employees")
    List<Employee> getAllEmployee() {
        employeeService.getAllEmployees()
    }

    @DeleteMapping("/employee/{employeeId}")
    Map deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        employeeService.deleteEmployee(employeeId)
        ["message" : "Employee deleted successfully"]
    }
}
