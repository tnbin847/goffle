package com.tnbin.goffle.domain.user.dto.enums;

import lombok.RequiredArgsConstructor;

/**
 * 회원가입 방식에 따른 로그인 유형에 대한 코드값을 정의한 {@link Enum}클래스
 */

@RequiredArgsConstructor
public enum LoginType {

    LOCAL ("LOCAL", "로컬 로그인 계정"),
    SOCIAL ("OAUTH", "소셜 로그인 계정");

    private final String code;

    private final String label;
}