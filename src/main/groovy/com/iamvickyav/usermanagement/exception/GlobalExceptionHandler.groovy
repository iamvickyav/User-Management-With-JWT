package com.iamvickyav.usermanagement.exception

import io.jsonwebtoken.ExpiredJwtException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class)

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorResponse> processBusinessException(BusinessException ex) {
        Exception exception = ex.originalException ?: ex
        generateErrorResponse(ex.errorCode, ex.message, exception)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    ResponseEntity processMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED)
    }

    @ExceptionHandler(ExpiredJwtException.class)
    ResponseEntity processExpiredJwtException(ExpiredJwtException ex) {
        generateErrorResponse(ErrorCode.INVALID_TOKEN, "Authorization header is invalid", ex)
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity processMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        generateErrorResponse(ErrorCode.INVALID_REQUEST, ex.bindingResult.fieldError.field + " " + ex.bindingResult.fieldError.defaultMessage, ex)
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> processBusinessException(Exception ex) {
        generateErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR, "Something went wrong", ex)
    }

    private ResponseEntity<ErrorResponse> generateErrorResponse(ErrorCode errorCode, String message, Exception ex) {
        logger.error("Exception Stack", ex)
        ErrorResponse errorResponse = new ErrorResponse(code: errorCode.code, message: message)
        return new ResponseEntity<ErrorResponse>(errorResponse, errorCode.httpStatus)
    }
}
