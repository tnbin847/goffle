package com.tnbin.goffle.domain.user.dto.enums;

import lombok.RequiredArgsConstructor;

/**
 * 권한에 대한 코드값을 정의한 {@link Enum}클래스
 */

@RequiredArgsConstructor
public enum Role {

    USER ("ROLE_USER", "일반회원"),
    ADMIN ("ROLE_ADMIN", "관리자");

    private final String code;

    private final String label;
}