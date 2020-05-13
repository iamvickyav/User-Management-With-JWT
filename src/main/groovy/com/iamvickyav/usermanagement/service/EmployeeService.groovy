package com.iamvickyav.usermanagement.service

import com.iamvickyav.usermanagement.entity.Employee
import com.iamvickyav.usermanagement.exception.BusinessException
import com.iamvickyav.usermanagement.exception.ErrorCode
import com.iamvickyav.usermanagement.model.CreateEmployeeRequest
import com.iamvickyav.usermanagement.model.EmployeeUpdateRequest
import com.iamvickyav.usermanagement.repo.EmployeeRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    Logger logger = LoggerFactory.getLogger(EmployeeService.class)

    @Autowired
    EmployeeRepo employeeRepo

    Integer createEmployee(CreateEmployeeRequest createEmployeeRequest) {

        Employee employee = employeeRepo.findByEmail(createEmployeeRequest.email)
        if(!employee) {
            employee = new Employee()
            employee.name = createEmployeeRequest.name
            employee.email = createEmployeeRequest.email
            employee.dept = createEmployeeRequest.dept
            employee.doj = new Date()
            employee = employeeRepo.save(employee)
            logger.info("Employee created with Email={}", employee.email)
            employee.id
        } else {
            throw new BusinessException(ErrorCode.DUPLICATE_RECORD, "Email id already exist")
        }
    }

    Employee getEmployeeData(Integer id) {

        Optional<Employee> employeeOptional = employeeRepo.findById(id)
        if(employeeOptional.isPresent())
            return employeeOptional.get()
        else
            throw new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee not found in our records !")
    }

    List<Employee> getAllEmployees() {
        employeeRepo.findAll()
    }

    void updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) {

        Optional<Employee> employeeOptional = employeeRepo.findById(employeeUpdateRequest.id)
        if(employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get()
            employee.dept = employeeUpdateRequest.dept
            logger.info("Employee record updated for id={}", employeeUpdateRequest.id)

            employeeRepo.save(employee)
        } else {
            logger.info("Employee record not found for id={}", employeeUpdateRequest.id)
            throw new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee not found in our records !")
        }
    }

    void deleteEmployee(int id) {
        try {
            employeeRepo.deleteById(id)
        } catch(EmptyResultDataAccessException ex) {
            throw new BusinessException(ErrorCode.EMPLOYEE_NOT_FOUND, "Employee not found in our records !")
        }
        logger.info("Employee record deleted for id={}", id)
    }
}
