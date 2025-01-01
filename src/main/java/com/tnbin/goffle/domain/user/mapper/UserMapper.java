package com.tnbin.goffle.domain.user.mapper;


import com.tnbin.goffle.domain.user.dto.SignUpRequest;
import com.tnbin.goffle.domain.user.dto.UserRoleRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 전달받은 이메일의 존재여부를 확인한다.
     */
    boolean existsByEmail(String email);

    /**
     * 회원가입 정보를 저장한다.
     */
    int insertUser(SignUpRequest signUpDto);

    /**
     * 사용자의 권한을 추가 저장한다.
     */
    void insertUserRole(UserRoleRequest userRoleRegDto);
}