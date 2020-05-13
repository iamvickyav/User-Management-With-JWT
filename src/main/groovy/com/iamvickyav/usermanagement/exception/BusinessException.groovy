package com.iamvickyav.usermanagement.exception

class BusinessException extends RuntimeException {

    ErrorCode errorCode
    String message
    Exception originalException

    BusinessException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode
        this.message = message
    }

    BusinessException(ErrorCode errorCode, String message, Exception exception) {
        this.errorCode = errorCode
        this.message = message
        this.originalException = exception
    }
}
