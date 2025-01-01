package com.tnbin.goffle.global.common.mybatis;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link CodeEnum}인터페이스를 구현한 {@link Enum}클래스에 정의된 코드값을 데이터베이스에 저장하거나, 데이터베이스로부터 가져온 코드값을
 * {@link Enum}으로 변환하는 역할을 수행하는 타입 핸들러 클래스
 *
 * @param <E> {@link CodeEnum}인터페이스를 구현하여 타입 핸들러를 통해 코드값을 저장하고자 하는 {@link Enum}
 */

@MappedTypes(CodeEnum.class)
public class CodeEnumTypeHandler<E extends Enum<E> & CodeEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    private final E[] constants;

    public CodeEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null.");
        }
        this.type = type;
        this.constants = type.getEnumConstants();
        if (!type.isInterface() && this.constants == null) {
            throw new IllegalArgumentException("'" + type.getSimpleName() + "' does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return convertColumnCodeToEnum(rs.getString(columnName));
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return convertColumnCodeToEnum(rs.getString(columnIndex));
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return convertColumnCodeToEnum(cs.getString(columnIndex));
    }

    /**
     * 데이터베이스로부터 가져온 코드값과 동일한 상수값을 정의한 {@link Enum}을 반환하며 존재하지 않는다면 {@code null}을 반환한다.
     *
     * @param columnCode    데이터베이스로부터 가져온 코드값
     * @return              전달된 코드값과 일치한 상수값을 정의한 {@link Enum} 또는 {@code null}
     */
    private E convertColumnCodeToEnum(String columnCode) {
        try {
            for (E codeEnum : constants) {
                if (codeEnum.getCode().equals(columnCode)) {
                    return codeEnum;
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot convert '" + columnCode + "' to '" + type.getSimpleName() + "'.");
        }
    }
}