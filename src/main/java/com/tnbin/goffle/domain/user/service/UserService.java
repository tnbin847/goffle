package com.tnbin.goffle.domain.user.service;


import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.dto.UserRoleRequest;
import com.tnbin.goffle.domain.user.dto.enums.LoginType;
import com.tnbin.goffle.domain.user.dto.enums.Role;
import com.tnbin.goffle.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public boolean existsUserByEmail(final String email) {
        return userMapper.existsByEmail(email);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(final SignUpRequest signUpDto) {
        signUpDto.setLoginType(LoginType.LOCAL);
        if (userMapper.insertUser(signUpDto) == 1) {
            UserRoleRequest userRoleRegDto = UserRoleRequest.registerBuild()
                    .userId(signUpDto.getUserId()).role(Role.USER).build();
            userMapper.insertUserRole(userRoleRegDto);
        }
    }
}