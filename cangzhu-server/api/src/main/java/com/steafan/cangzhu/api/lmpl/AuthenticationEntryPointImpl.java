package com.steafan.cangzhu.api.lmpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steafan.cangzhu.api.utils.WebUtils;
import com.steafan.cangzhu.api.controller.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security查询到网址需要token验证后返回的response
 */
@Component
@RequiredArgsConstructor
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        BaseResponse<Void> result = BaseResponse.fail(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        String json = objectMapper.writeValueAsString(result);
        WebUtils.renderString(response, json, HttpStatus.UNAUTHORIZED.value());
    }
}
