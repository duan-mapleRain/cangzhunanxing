package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.TokenDTO;
import com.steafan.cangzhu.controller.request.user.AddUserDTO;
import com.steafan.cangzhu.controller.request.user.UpdateInfoDTO;
import com.steafan.cangzhu.controller.request.user.UpdatePasswdDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated

public class UserController {

    private final UserService userService;

    @PostMapping("/add/user")
    public BaseResponse<Integer> add(@Valid @RequestBody AddUserDTO registerDTO) {
        return BaseResponse.success("注册成功", userService.add(registerDTO));
    }

    @PostMapping("/update/passwd")
    public BaseResponse<Void> update_passwd(@AuthenticationPrincipal TokenDTO tokenDTO,
                                            @Valid @RequestBody UpdatePasswdDTO updatePasswdDTO) {
        userService.update_passwd(tokenDTO,updatePasswdDTO);
        return BaseResponse.success();
    }

    @PostMapping("/update/info")
    public BaseResponse<Void> update_user_info(@RequestBody @AuthenticationPrincipal TokenDTO tokenDTO,
                                               @Valid @RequestBody UpdateInfoDTO updateInfoDTO) {
        userService.update_user_info(tokenDTO, updateInfoDTO);
        return BaseResponse.success();
    }




}
