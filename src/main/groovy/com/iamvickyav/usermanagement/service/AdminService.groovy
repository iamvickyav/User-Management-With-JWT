package com.iamvickyav.usermanagement.service

import com.iamvickyav.usermanagement.entity.User
import com.iamvickyav.usermanagement.exception.BusinessException
import com.iamvickyav.usermanagement.exception.ErrorCode
import com.iamvickyav.usermanagement.model.LoginRequest
import com.iamvickyav.usermanagement.model.NewPasswordRequest
import com.iamvickyav.usermanagement.repo.AdminRepo
import com.iamvickyav.usermanagement.security.Authorizer
import org.jasypt.util.password.BasicPasswordEncryptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class AdminService {

    Logger logger = LoggerFactory.getLogger(AdminService.class)

    @Autowired
    AdminRepo adminRepo

    @Autowired
    EmailService emailService

    @Autowired
    Authorizer authorizer

    BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor()

    String loginUser(LoginRequest loginRequest) {

        User user = adminRepo.findByEmail(loginRequest.email)

        if(!user) {
            logger.info("Invalid Email/Password for email={}", loginRequest.email)
            throw new BusinessException(ErrorCode.INVALID_LOGIN_DETAILS, "Email id not available in our records")
        } else if(!validatePassword(loginRequest.password, user.password)) {
            logger.info("Incorrect Password for email={}", loginRequest.email)
            throw new BusinessException(ErrorCode.INVALID_LOGIN_DETAILS, "Email/Password is incorrect")
        } else {
            updateUserLastLogin(user)

            logger.info("Login successful, sending JWT for user with Email= {}", loginRequest.email)
            authorizer.generateToken(loginRequest.email)
        }
    }

    void changeUserPassword(NewPasswordRequest newPasswordRequest) {

        User user = adminRepo.findByEmail(newPasswordRequest.email)

        if(user) {
            if(!emailService.validateToken(newPasswordRequest.email, newPasswordRequest.tokenFromEmail)) {
                throw new BusinessException(ErrorCode.INVALID_TOKEN, "Token sent in Email not matching with token sent by user")
            }

            String encryptedPassword = encryptPassword(newPasswordRequest.newPassword)
            user.setPassword(encryptedPassword)
            adminRepo.updateUserPassword(encryptedPassword, user.userId)
        } else {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "Email Id send is not registered with us !")
        }
    }

    boolean validatePassword(String passwordFromUser, String passwordFromDB) {
        passwordEncryptor.checkPassword(passwordFromUser, passwordFromDB)
    }

    private String encryptPassword(String password) {
        passwordEncryptor.encryptPassword(password)
    }

    @Async
    void updateUserLastLogin(User user) {
        logger.info("Updating last login time for User witj Email= {}", user.email)
        user.lastLogin = new Date()
        adminRepo.save(user)
    }
}
