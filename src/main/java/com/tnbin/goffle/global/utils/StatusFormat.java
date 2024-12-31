package com.tnbin.goffle.global.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 *
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
     *
     * @param value boolean 타입으로 변환하고자 하는 1 또는 0
     * @return      인자값이 1일 경우 {@code true}를, 0일 경우 {@code false}
     */
    public static boolean numberToBoolean(int value) {
        return Arrays.stream(values())
                .filter(format -> format.getNumber() == value)
                .findFirst()
                .map(StatusFormat::isBool)
                .orElseThrow(() -> new IllegalArgumentException("cannot convert '" + value + "' to boolean type."));
    }
}