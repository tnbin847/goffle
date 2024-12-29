package com.tnbin.goffle.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 클라이언트의 요청에 의해 발생된 유효성 검증 에러를 처리 및 그 정보를 리스트로 반환하는 역할을 수행하는 클래스
 */

@Getter
public class ValidationErrors {

    private final String field;

    private final String value;

    private final String message;

    @Builder
    private ValidationErrors(String field, String value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }

    /**
     * 특정 필드에 의해 발생된 에러에 대한 정보를 반환한다.
     *
     * @param field     에러가 발생된 필드명
     * @param value     필드에 담긴 사용자의 입력값
     * @param message   에러가 발생된 원인
     * @return          에러 정보 리스트
     */
    public static List<ValidationErrors> of(final String field, final String value, final String message) {
        final List<ValidationErrors> errors = new ArrayList<>();
        errors.add(ValidationErrors.builder().field(field).message(message).value(value).build());
        return errors;
    }

    /**
     * 요청 파라미터의 유효성 검증 실패로 인해 발생된 에러에 대한 정보를 반환한다.
     *
     * @param bindingResult 스프링에서 제공하는 폼 데이터 바인딩 과정 중에 발생되는 검증 오류를 수집 및 처리하는 객체
     * @return              에러 정보 리스트
     */
    public static List<ValidationErrors> of(final BindingResult bindingResult) {
        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return fieldErrors.stream().map(fieldError -> ValidationErrors.builder()
                .field(fieldError.getField())
                .value(rejectedToString(fieldError.getRejectedValue()))
                .message(fieldError.getDefaultMessage())
                .build()).collect(Collectors.toList());
    }

    /**
     * 요청 파라미터의 유효성 검증 실패로 인해 발생된 에러에 대한 정보를 반환한다.
     *
     * @param violations    검증 실패 원인에 대한 정보를 담고 있는 객체
     * @return              에러 정보 리스트
     */
    public static List<ValidationErrors> of(final Set<ConstraintViolation<?>> violations) {
        final List<ConstraintViolation<?>> constraintViolations = new ArrayList<>(violations);
        return constraintViolations.stream().map(constraintViolation -> ValidationErrors.builder()
                .field(parsingPropertyName(constraintViolation.getPropertyPath().toString()))
                .value(rejectedToString(constraintViolation.getInvalidValue()))
                .message(constraintViolation.getMessageTemplate())
                .build()
        ).collect(Collectors.toList());
    }

    private static String rejectedToString(Object rejected) {
        return rejected == null ? "" : rejected.toString();
    }

    private static String parsingPropertyName(String propertyPath) {
        final int lastIndex = propertyPath.lastIndexOf('.');
        return lastIndex == -1 ? propertyPath : propertyPath.substring(lastIndex);
    }
}