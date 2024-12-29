package com.tnbin.goffle.global.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 상태여부 컬럼 값을 처리하기 위해 서로 다른 타입의 값들을 상응되는 의미별로 정의한 {@link Enum}클래스
 */

@RequiredArgsConstructor
@Getter
public enum StatusFormat {

    YES (1, "Y", true),
    NO (0, "N", false);

    private final int number;
    private final String symbol;
    private final boolean bool;

    /**
     * 전달된 값이 {@code 1}일 경우 {@code true}를 {@code 0}일 경우 {@code false}를 반환하며, 그 외의 값일 경우
     * {@link IllegalArgumentException}을 발생시킨다.
     */
    public static boolean numberToBoolean(int value) {
        return Arrays.stream(values())
                .filter(format -> format.getNumber() == value)
                .findFirst()
                .map(StatusFormat::isBool)
                .orElseThrow(() -> new IllegalArgumentException("Cannot convert '" + value + "' to boolean type."));
    }
}