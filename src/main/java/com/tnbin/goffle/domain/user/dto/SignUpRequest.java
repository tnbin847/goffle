package com.tnbin.goffle.domain.user.dto;

import com.tnbin.goffle.domain.user.dto.enums.LoginType;
import com.tnbin.goffle.global.utils.StatusFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpRequest {

    private Long id;

    @NotBlank(message = "이름을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]{2,4}$", message = "이름은 2~4자의 한글로 입력해주세요.")
    private String name;

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^[A-Za-z0-9]{4,20}$", message = "아이디는 영문과 숫자 조합의 4~20자로 입력해주세요.")
    private String loginId;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일의 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,16}$", message = "비밀번호는 영문과 숫자, 특수문자 조합의 8~16자로 입력해주세요.")
    @Setter
    private String password;

    @NotBlank(message = "비밀번호를 한 번 더 입력해주세요.")
    private String passwordConfirm;

    @Setter
    private LoginType loginType;

    private final int enabled = StatusFormat.YES.getNumber();

    private final String useYn = StatusFormat.YES.getSymbol();

    private final String deleteYn = StatusFormat.NO.getSymbol();
}