package com.steafan.cangzhu.api.lmpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steafan.cangzhu.api.utils.WebUtils;
import com.steafan.cangzhu.api.controller.request.TokenDTO;
import com.steafan.cangzhu.api.controller.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security查询到权限不足后返回的response
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //获取当前用户的权限
        TokenDTO user = (TokenDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BaseResponse<Void> result;
        if (user.getUserDAO().getStatus() == 0) {
            result = BaseResponse.fail(HttpStatus.FORBIDDEN.value(), "当前账户尚未激活");
        } else {
            result = BaseResponse.fail(HttpStatus.FORBIDDEN.value(), "权限不足");
        }
        String json = new ObjectMapper().writeValueAsString(result);
        WebUtils.renderString(response, json, HttpStatus.FORBIDDEN.value());
    }
}
