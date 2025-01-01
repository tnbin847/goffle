package com.tnbin.goffle.domain.user.dto;


import com.tnbin.goffle.domain.user.dto.enums.Role;
import com.tnbin.goffle.global.utils.StatusFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserRoleRequest {

    private Long userId;

    private Role role;

    private String useYn;

    private LocalDateTime updatedDatetime;

    @Builder(builderClassName = "RegisterBuild", builderMethodName = "registerBuild")
    public UserRoleRequest(Long userId, Role role) {
        this.userId = userId;
        this.role = role;
        this.useYn = StatusFormat.YES.getSymbol();
        this.updatedDatetime = LocalDateTime.now();
    }
}