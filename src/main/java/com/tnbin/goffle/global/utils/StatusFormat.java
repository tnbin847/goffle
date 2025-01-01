package com.tnbin.goffle.global.utils;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 데이터베이스 테이블 내의 상태여부 컬럼의 값을 처리하기 위해 서로 다른 타입의 값들을 상응되는 의미별로 정의한 {@link Enum}클래스
 */

@RequiredArgsConstructor
@Getter
public enum StatusFormat {

    YES (1, "Y", true),
    NO (0, "N", false);

    private final int numeric;

    private final String symbol;

    private final boolean bool;

    /**
     * 전달된 값이 {@code 1}일 경우 {@code true}를, {@code 0}일 경우 {@code false}를 반환한다.
     *
     * @param num   논리형의 상태값으로 변환하고자 하는 정수형 값
     * @return      인자 값에 따른 {@code true} 또는 {@code false}
     */
    public static boolean numberToBoolean(int num) {
        return Arrays.stream(values())
                .filter(format -> format.getNumeric() == num)
                .findFirst()
                .map(StatusFormat::isBool)
                .orElseThrow(() -> new IllegalArgumentException("Cannot conver '" + num + "' to boolean type."));
    }
}