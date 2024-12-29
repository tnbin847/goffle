package com.tnbin.goffle.global.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * API 응답 코드와 관련된 정보들을 정의 및 관리하기 위한 {@link Enum}클래스
 */

@RequiredArgsConstructor
@Getter
public enum ResponseEnum {

    /**
     * 성공 응답 코드
     */
    OK (100000, HttpStatus.OK, "요청이 성공했습니다."),


    /**
     * 공통 에러 응답 코드
     */
    INVALID_REQUEST_PARAM (-800100, HttpStatus.BAD_REQUEST, "요청 파라미터의 값이 유효하지 않습니다."),
    INVALID_REQUEST_PARAM_TYPE (-800101, HttpStatus.BAD_REQUEST, "유효하지 않은 타입의 요청 파라미터입니다."),
    NOT_FOUND_RESOURCE (-804200, HttpStatus.NOT_FOUND, "요청하신 자원을 찾을 수 없습니다."),
    NOT_SUPPORTED_METHOD (-805200, HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드 방식의 요청입니다."),
    INTERNAL_SERVER_ERROR (-900000, HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버에 오류가 발생했습니다."),

    /**
     * 사용자 관련 응답 코드
     */
    NOT_FOUND_USER (-804210, HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."),
    UNAUTHORIZED_USER (-801310, HttpStatus.UNAUTHORIZED, "인증되지 않았습니다."),
    FORBIDDEN_USER (-803310, HttpStatus.FORBIDDEN, "접근 권한이 업습니다.")
    ;

    private final int code;

    private final HttpStatus status;

    private final String message;
}