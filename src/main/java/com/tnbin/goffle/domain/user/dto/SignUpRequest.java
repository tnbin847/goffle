package com.tnbin.goffle.domain.user.dto;


import com.tnbin.goffle.domain.user.dto.enums.LoginType;
import com.tnbin.goffle.global.utils.StatusFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpRequest {

    private Long userId;

    private String email;

    @Setter
    private String password;

    private String passwordConfirm;

    private String name;

    @Setter
    private LoginType loginType;

    private final int enabledAt = StatusFormat.YES.getNumeric();

    private final String useYn = StatusFormat.YES.getSymbol();

    private final String deleteYn = StatusFormat.NO.getSymbol();
}