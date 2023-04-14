package com.steafan.cangzhu.api.controller;


import com.steafan.cangzhu.api.service.UserService;
import com.steafan.cangzhu.api.controller.request.user.LoginDTO;
import com.steafan.cangzhu.api.controller.response.BaseResponse;
import com.steafan.cangzhu.api.controller.response.TokenResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("")
@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "System", description = "系统管理接口")
public class SystemController {

    private final UserService userService;

    @GetMapping("/")
    @ApiResponse(description = "系统启动信息")
    public BaseResponse<String> test() {
        return BaseResponse.success("CZ Server is Running", null);
    }

    @PostMapping("/login")
    public BaseResponse<TokenResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        return BaseResponse.success("登录成功", userService.login(loginDTO));
    }
}
