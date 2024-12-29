package com.tnbin.goffle.domain.user.dto.enums;

import com.tnbin.goffle.global.common.mybatis.CodeEnum;
import lombok.RequiredArgsConstructor;

/**
 * 사용자에게 부여될 권한에 대한 코드를 정의한 {@link Enum}클래스
 */

@RequiredArgsConstructor
public enum Role implements CodeEnum {

    USER ("ROLE_USER", "일반회원"),
    MANAGER ("ROLE_MANAGER", "시스템 매니저"),
    ADMIN ("ROLE_ADMIN", "관리자");

    private final String code;

    private final String label;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}