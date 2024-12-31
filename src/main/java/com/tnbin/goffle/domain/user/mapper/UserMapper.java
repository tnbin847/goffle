package com.tnbin.goffle.domain.user.mapper;

import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.dto.UserRoleRequest;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    /**
     * 요청된 아이디의 존재여부를 확인한다.
     */
    void existsByLoginId(String loginId);

    /**
     * 요청된 이메일의 존재여부를 확인한다.
     */
    void existsByEmail(String email);

    /**
     * 회원가입 정보를 저장한다.
     */
    int insertUser(SignUpRequest signUpReqDto);

    /**
     * 가입된 회원의 권한을 저장한다.
     */
    void insertUserRole(UserRoleRequest userRoleReqDto);
}