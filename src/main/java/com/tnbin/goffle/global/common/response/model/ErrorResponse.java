package com.tnbin.goffle.global.common.response.model;

import com.tnbin.goffle.global.common.response.ResponseEnum;
import com.tnbin.goffle.global.error.ValidationErrors;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;


@Getter
public class ErrorResponse extends CommonResponse {

    private List<ValidationErrors> errors;

    @Builder
    private ErrorResponse(int code, String message, List<ValidationErrors> errors) {
        super(code, message);
        this.errors = errors == null ? Collections.emptyList() : errors;
    }

    @Builder
    private ErrorResponse(int code, String message) {
        super(code, message);
    }

    /**
     * 실패 응답을 반환한다.
     *
     * @param responseEnum  에러 응답에 관련된 정보를 정의한 {@link Enum}
     * @param errors        에러 정보 리스트
     * @return              응답 객체
     */
    public static ErrorResponse fail(final ResponseEnum responseEnum, final List<ValidationErrors> errors) {
        return ErrorResponse.builder()
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .errors(errors)
                .build();
    }

    /**
     * 에러 응답을 반환한다.
     *
     * @param responseEnum  에러 응답에 관련된 정보를 정의한 {@link Enum}
     * @return              응답 객체
     */
    public static ErrorResponse error(final ResponseEnum responseEnum) {
        return ErrorResponse.builder()
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .build();
    }
}