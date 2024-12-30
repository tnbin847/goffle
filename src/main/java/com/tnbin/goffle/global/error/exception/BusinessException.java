package com.tnbin.goffle.global.error.exception;

import com.tnbin.goffle.global.common.response.ResponseEnum;
import com.tnbin.goffle.global.error.ValidationErrors;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 *  비즈니스 로직에서 발생하는 예외를 처리하기 위해 정의한 사용자 정의 예외 클래스
 */

@Getter
public class BusinessException extends RuntimeException {

    private final ResponseEnum resposneEnum;

    private List<ValidationErrors> errors = new ArrayList<>();

    public BusinessException(String message, ResponseEnum resposneEnum) {
        super(message);
        this.resposneEnum = resposneEnum;
    }

    public BusinessException(ResponseEnum resposneEnum) {
        super(resposneEnum.getMessage());
        this.resposneEnum = resposneEnum;
    }

    public BusinessException(List<ValidationErrors> errors, ResponseEnum resposneEnum) {
        super(resposneEnum.getMessage());
        this.errors = errors;
        this.resposneEnum = resposneEnum;
    }
}