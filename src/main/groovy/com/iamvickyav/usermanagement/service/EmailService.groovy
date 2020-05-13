package com.iamvickyav.usermanagement.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmailService {

    Logger logger = LoggerFactory.getLogger(EmailService.class)

    static final String TOKEN_SENT_IN_EMAIL = "12345"

    void sendPasswordResetTokenEmail() {
        logger.info("Password reset token email sent")
    }

    boolean validateToken(String email, String tokenFromEmail) {
        TOKEN_SENT_IN_EMAIL.equals(tokenFromEmail)
    }
}
