package com.tnbin.goffle.domain.user.mapper;

import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.dto.UserRoleRequest;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    /**
     * 전달받은 아이디가 이미 존재한다면 {@code true}, 아니면 {@code false}를 반환한다.
     */
    boolean existsByLoginId(String loginId);

    /**
     * 전달받은 이메일가 이미 존재한다면 {@code true}, 아니면 {@code false}를 반환한다.
     */
    boolean existsByEmail(String email);

    /**
     * 가입 정보를 저장한다.
     */
    int insertUser(SignUpRequest signUpDto);

    /**
     * 가입된 사용자의 권한을 저장한다.
     */
    void insertUserRole(UserRoleRequest userRoleRegDto);
}