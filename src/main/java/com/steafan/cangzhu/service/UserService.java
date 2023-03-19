package com.steafan.cangzhu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.UpdateInfoDTO;
import com.steafan.cangzhu.controller.request.user.UpdatePasswdDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.controller.response.ResponseCode;
import com.steafan.cangzhu.mapper.UserMapper;
import com.steafan.cangzhu.repository.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

/**
 * @author AnselYuki
 */
@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Resource
    private UserMapper userMapper;

    //
    public BaseResponse<Integer> register(RegisterDTO registerDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("email", registerDTO.getEmail());
        if (userMapper.exists(queryWrapper)) {
            return BaseResponse.success(ResponseCode.REGISTER_FAIL);
        }
        User user = new User();
        //todo 加盐
        String encode = md5DigestAsHex((registerDTO.getPassword().getBytes()));
        user.setEmail(registerDTO.getEmail());
        user.setPassword(encode);
        user.setStatus(0);
        if (userMapper.insert(user) == 0) {
            return BaseResponse.success(ResponseCode.REGISTER_FAIL);
        }
        return BaseResponse.success(ResponseCode.REGISTER_SUCCESS);
    }


    public BaseResponse<Void> update_passwd(UpdatePasswdDTO updatePasswdDTO) {
        return BaseResponse.success(ResponseCode.UPDATE_PASSWORD_SUCCESS);
    }

    public BaseResponse<Void> update_user_info(UpdateInfoDTO updateInfoDTO) {
        return BaseResponse.success(ResponseCode.UPDATE_INFO_SUCCESS);
    }
}
