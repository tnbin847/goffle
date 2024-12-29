package com.tnbin.goffle.global.common.response.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 성공 응답과 에러 응답 모두 필수적으로 가지고 있어야 할 속성들을 정의한 공통 웅답 클래스
 */

@Getter
public class CommonResponse {

    /**
     * 요청에 대한 응답 일자
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "", timezone = "Asia/Seoul")
    private final LocalDateTime responseAt = LocalDateTime.now();

    /**
     * 요청 처리 상태를 나타내는 응답 코드
     */
    private final int code;

    /**
     * 응답 메시지
     */
    private final String message;

    public CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}