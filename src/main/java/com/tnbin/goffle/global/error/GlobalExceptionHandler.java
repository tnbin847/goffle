package com.tnbin.goffle.global.error;

import com.tnbin.goffle.global.common.response.ResponseEnum;
import com.tnbin.goffle.global.common.response.model.ErrorResponse;
import com.tnbin.goffle.global.error.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;


/**
 * 전역 예외 처리 클래스
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleRequestValidException(BindException e) {
        LOGGER.error("handing RequestValidException...!\n{}", parseExceptionStackTrace(e, null));
        return ErrorResponse.fail(ResponseEnum.INVALID_REQUEST_PARAM, ValidationErrors.of(e.getBindingResult()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        LOGGER.error("handling ConstraintViolationException...!\n{}", parseExceptionStackTrace(e, null));
        return ErrorResponse.fail(ResponseEnum.INVALID_REQUEST_PARAM, ValidationErrors.of(e.getConstraintViolations()));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        LOGGER.error("handling MethodArgumentTypeMismatchException...!\n{}", parseExceptionStackTrace(e, null));
        return ErrorResponse.fail(ResponseEnum.INVALID_REQUEST_PARAM_TYPE, ValidationErrors.of(e));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        final StringBuilder builder = new StringBuilder();
        final String message = builder.append(e.getMethod())
                                      .append(" method is not supported for this request.").toString();
        LOGGER.error("handling HttpRequestMethodNotSupportedException...!\n{}", parseExceptionStackTrace(e, message));
        return ErrorResponse.fail(ResponseEnum.NOT_SUPPORTED_METHOD, null);
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        LOGGER.error("handling BusinessException...!\n{}", parseExceptionStackTrace(e, null));
        final ResponseEnum responseEnum = e.getResposneEnum();
        return responseEnum.getStatus().is4xxClientError() ?
                ErrorResponse.fail(responseEnum, e.getErrors()) : ErrorResponse.error(responseEnum);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception e) {
        LOGGER.error("handling Exception...!\n{}", parseExceptionStackTrace(e, null));
        return ErrorResponse.error(ResponseEnum.INTERNAL_SERVER_ERROR);
    }

    private String parseExceptionStackTrace(Exception e, String message) {
        if (LOGGER.isErrorEnabled()) {
            final StackTraceElement[] traceElements = e.getStackTrace();
            final StringBuffer buffer = new StringBuffer();
            buffer.append(traceElements[0].getClassName()).append(" : ").append(traceElements[0].getLineNumber())
                  .append(" Line, in").append(traceElements[0].getMethodName()).append("() - ").append(e.getMessage());
            if (StringUtils.hasText(message)) {
                buffer.append("\n").append(">> ").append(message);
            }
            return buffer.toString();
        }
        return "";
    }
}