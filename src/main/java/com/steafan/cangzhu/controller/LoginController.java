package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.TokenDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.mapper.UserMapper;
import com.steafan.cangzhu.service.LoginService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RequestMapping("/sign")
@RestController
@Validated
@RequiredArgsConstructor

public class LoginController {
    private final LoginService loginService;

    @ApiResponse
    @PostMapping("/in")
    public BaseResponse<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        return loginService.in(registerDTO);
    }

    @PostMapping("/update")
    public BaseResponse<Void> update(@RequestBody TokenDTO tokenDTO) {
        return loginService.update(tokenDTO);
    }

    @PostMapping("/out")
    public BaseResponse<Void> out(@RequestBody TokenDTO tokenDTO) {
        return loginService.out(tokenDTO);
    }
}
