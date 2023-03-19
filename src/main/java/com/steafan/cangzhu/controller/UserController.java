package com.steafan.cangzhu.controller;

import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.UpdateInfoDTO;
import com.steafan.cangzhu.controller.request.user.UpdatePasswdDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated

public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<Integer> register(@Valid @RequestBody RegisterDTO registerDTO) {
        System.out.println("in here");
        return userService.register(registerDTO);
    }

    @PostMapping("/update/passwd")
    public BaseResponse<Void> update_passwd(@Valid @RequestBody UpdatePasswdDTO updatePasswdDTO) {
        return userService.update_passwd(updatePasswdDTO);
    }

    @PostMapping("/update/info")
    public BaseResponse<Void> update_user_info(@Valid @RequestBody UpdateInfoDTO updateInfoDTO) {
        return userService.update_user_info(updateInfoDTO);
    }



}
