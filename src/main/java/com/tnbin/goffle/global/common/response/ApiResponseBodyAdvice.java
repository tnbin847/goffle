package com.tnbin.goffle.global.common.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tnbin.goffle.global.common.response.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collections;
import java.util.List;

/**
 * 성공 응답을 클라이언트에게 반환할 때 응답 바디부 내의 결과 데이터가 {@code null}일 경우, 반환 타입에 따라
 * 빈 객체 또는 빈 배열을 담아 반환하는 역할을 수행하는 클래스
 */

@RestControllerAdvice(basePackages = "com.tnbin.goffle.domain")
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        final Class<?> type = returnType.getParameterType();
        if (body == null) {
            body = generateDefaultCollection(type);
        }

        final ApiResponse<?> apiResponse = ApiResponse.success(ResponseEnum.OK, body);
        if (MappingJackson2HttpMessageConverter.class.isAssignableFrom(selectedConverterType)) {
            return apiResponse;
        }

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            response.getHeaders().set("Content-Type", "application/json");
            return objectMapper.registerModule(new JavaTimeModule()).writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 컬렉션 객체를 활용해 전달받은 타입이 {@link List} 또는 그 하위 객체일 때 빈 배열, 그게 아니라면 빈 객체를 반환한다.
     * 
     * @param type  메소드의 반환 타입
     * @return      빈 배열 또는 빈 객체
     */
    private Object generateDefaultCollection(final Class<?> type) {
        return List.class.isAssignableFrom(type) ? Collections.emptyList() : Collections.emptyMap();
    }
}