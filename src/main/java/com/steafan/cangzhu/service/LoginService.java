package com.steafan.cangzhu.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.TokenDTO;
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

@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Resource
    private UserMapper userMapper;

    public BaseResponse<String> in(RegisterDTO registerDTO) {
        String encode;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("email", registerDTO.getEmail()).eq("password", md5DigestAsHex((registerDTO.getPassword().getBytes())));
        System.out.println(userMapper.exists(queryWrapper));
        if (!userMapper.exists(queryWrapper)) {
            return BaseResponse.fail(ResponseCode.REGISTER_FAIL);
        }
        String userInfo = registerDTO.getEmail() + System.currentTimeMillis();
        String token = Arrays.toString(DigestUtils.md5Digest(userInfo.getBytes()));
        //todo 获得权限列表，并以hashmap的形式存储入redis，设置过期时间
        return BaseResponse.success(token);
    }

    public BaseResponse<Void> update(TokenDTO tokenDTO) {
        //判断redis中是否存在，如果存在则更新过期时间
        return BaseResponse.success();
    }

    public BaseResponse<Void> out(TokenDTO tokenDTO) {
        //判断redis中是否存在，存在则删除token
        return null;
    }


}
