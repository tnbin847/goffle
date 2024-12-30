package com.tnbin.goffle.domain.user.service;

import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.dto.UserRoleRequest;
import com.tnbin.goffle.domain.user.dto.enums.LoginType;
import com.tnbin.goffle.domain.user.dto.enums.Role;
import com.tnbin.goffle.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public boolean existsUserByLoginId(String loginId) {
        return userMapper.existsByLoginId(loginId);
    }

    @Transactional(readOnly = true)
    public boolean existsUserByEmail(String email) {
        return userMapper.existsByEmail(email);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(final SignUpRequest signUpDto) {
        final String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        signUpDto.setPassword(encodedPassword);
        signUpDto.setLoginType(LoginType.LOCAL);
        if (userMapper.insertUser(signUpDto) == 1) {
            final UserRoleRequest userRoleRegDto = UserRoleRequest.userRoleRegisterBuilder()
                    .userId(signUpDto.getId()).role(Role.USER).build();
            userMapper.insertUserRole(userRoleRegDto);
        }
    }
}