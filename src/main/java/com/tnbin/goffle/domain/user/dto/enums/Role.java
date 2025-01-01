package com.tnbin.goffle.domain.user.dto.enums;

import com.tnbin.goffle.global.common.mybatis.CodeEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role implements CodeEnum {

    USER ("ROLE_USER", "일반회원"),
    ADMIN ("ROLE_ADMIN", "관리자")
    ;

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