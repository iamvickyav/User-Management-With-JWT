package com.iamvickyav.usermanagement.controller


import com.iamvickyav.usermanagement.model.ChangePasswordRequest
import com.iamvickyav.usermanagement.model.LoginRequest
import com.iamvickyav.usermanagement.model.NewPasswordRequest
import com.iamvickyav.usermanagement.service.AdminService
import com.iamvickyav.usermanagement.service.EmailService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@RestController
class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class)

    @Autowired
    AdminService adminService

    @Autowired
    EmailService emailService

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Login request received for User with Email={}", loginRequest.email)
        adminService.loginUser(loginRequest)
    }

    @RequestMapping(value = "/password/change", method = RequestMethod.POST)
    Map changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        logger.info("Password change request received for User with Email={}", changePasswordRequest.email)
        emailService.sendPasswordResetTokenEmail()
        return ["message" : "Password reset token sent to your email"]
    }

    @RequestMapping(value = "/password/recreate", method = RequestMethod.POST)
    Map changePasswordWithToken(@Valid @RequestBody NewPasswordRequest newPasswordRequest) {
        logger.info("New Password creation request received for User with Email={}", newPasswordRequest.email)

        adminService.changeUserPassword(newPasswordRequest)
        return ["message" : "Password reset successful"]
    }
}
