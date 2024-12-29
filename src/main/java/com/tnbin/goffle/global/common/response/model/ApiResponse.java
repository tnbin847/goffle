package com.tnbin.goffle.global.common.response.model;

import com.tnbin.goffle.global.common.response.ResponseEnum;
import lombok.Builder;
import lombok.Getter;


@Getter
public class ApiResponse<T> extends CommonResponse {

    private final T data;

    @Builder
    private ApiResponse(int code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    /**
     * 성공 응답을 반환한다.
     *
     * @param responseEnum  성공 응답 코드 관련 정보를 정의한 {@link Enum}클래스
     * @param data          응답 결과 데이터
     * @return              응답 객체
     * @param <T>           응답 결과 데이터의 타입
     */
    public static <T> ApiResponse<T> success(final ResponseEnum responseEnum, final T data) {
        return ApiResponse.<T>builder()
                .code(responseEnum.getCode())
                .message(responseEnum.getMessage())
                .data(data)
                .build();
    }
}