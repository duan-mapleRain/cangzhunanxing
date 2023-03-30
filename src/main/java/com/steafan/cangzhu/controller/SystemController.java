package com.steafan.cangzhu.controller;


import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.controller.response.TokenResponse;
import com.steafan.cangzhu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
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
    public BaseResponse<TokenResponse> in(@Valid @RequestBody RegisterDTO registerDTO) {
        return BaseResponse.success("登录成功", userService.in(registerDTO));
    }
}
