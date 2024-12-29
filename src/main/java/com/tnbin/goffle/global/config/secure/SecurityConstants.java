package com.tnbin.goffle.global.config.secure;

/**
 * 스프링 시큐리티 처리를 위한 상수들을 정의한 상수 클래스
 */

public final class SecurityConstants {

    /**
     * 인증 요구 없이 접근가능한 요청 URL들을 정의한 상수 배열
     */
    public static final String[] PUBLICY_REQUEST_URL_MATCHERS = {
            "/",
            "/sign-up",
            "/api/v1/login",
            "/api/v1/logout",
            "/api/v1/user/exists/**",
            "/api/v1/user"
    };

    /**
     * 폼 로그인 인증 처리를 위한 접근 주체 파라미터 명
     */
    public static final String FORM_USERNAME_KEY = "loginId";

    /**
     * 폼 로그인 인증 처리를 위한 비밀번호 파라미터 명
     */
    public static final String FORM_PASSWORD_KEY = "password";
}