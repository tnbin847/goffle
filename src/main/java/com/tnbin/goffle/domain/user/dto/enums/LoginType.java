package com.tnbin.goffle.domain.user.dto.enums;


import com.tnbin.goffle.global.common.mybatis.CodeEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoginType implements CodeEnum {

    LOCAL ("LOCAL", "일반 로그인"),
    SOCIAL ("OAUTH", "소셜 로그인")
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