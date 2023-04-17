package com.steafan.cangzhu.api.controller;

import com.steafan.cangzhu.api.controller.request.TokenDTO;
import com.steafan.cangzhu.api.service.UserService;
import com.steafan.cangzhu.api.controller.request.user.AddUserDTO;
import com.steafan.cangzhu.api.controller.request.user.UpdateInfoDTO;
import com.steafan.cangzhu.api.controller.request.user.UpdatePasswdDTO;
import com.steafan.cangzhu.api.controller.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Maple
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/add")
    public BaseResponse<Integer> add(@Valid @RequestBody AddUserDTO registerDTO) {
        return BaseResponse.success("添加成功", userService.add(registerDTO));
    }

    @PostMapping("/update/passwd")
    public BaseResponse<Void> updatePasswd(@AuthenticationPrincipal TokenDTO tokenDTO,
                                           @Valid @RequestBody UpdatePasswdDTO updatePasswdDTO) {
        userService.updatePasswd(tokenDTO,updatePasswdDTO);
        return BaseResponse.success();
    }

    @PostMapping("/update/info")
    public BaseResponse<Void> updateUserInfo(@RequestBody @AuthenticationPrincipal TokenDTO tokenDTO,
                                             @Valid @RequestBody UpdateInfoDTO updateInfoDTO) {
        userService.updateUserInfo(tokenDTO, updateInfoDTO);
        return BaseResponse.success();
    }




}
