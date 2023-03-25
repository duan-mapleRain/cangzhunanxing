package com.steafan.cangzhu.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.user.LoginUser;
import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.TokenDTO;
import com.steafan.cangzhu.controller.response.BaseResponse;
import com.steafan.cangzhu.controller.response.ResponseCode;
import com.steafan.cangzhu.mapper.UserMapper;
import com.steafan.cangzhu.repository.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.DigestUtils.md5DigestAsHex;

@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    @Resource
    private UserMapper userMapper;

    @Value("duanjiamin")
    private String secret;
    @Value("3600")
    private int expire;

    private final AuthenticationManager authenticationManager;

    public BaseResponse<String> in(RegisterDTO registerDTO) {
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                registerDTO.getEmail(),
                registerDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticate)) {

        }

        LoginUser principal = (LoginUser) authenticate.getPrincipal();
        String userId = String.valueOf(principal.getUser().getEmail());
        String token = RandomStringUtils.random(16, true, true);
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.SECOND, expire);
        // 签发JwtToken，从上到下为设置签发时间，过期时间与生效时间
        Map<String, Object> payload = new HashMap<>(4) {
            {
                put(JWTPayload.ISSUED_AT, now.getTime());
                put(JWTPayload.EXPIRES_AT, newTime.getTime());
                put(JWTPayload.NOT_BEFORE, now.getTime());
                put("userId", userId);
                put("token", token);
            }
        };

        // 把完整的用户信息存入Redis，UserID作为Key

        String jwt = JWTUtil.createToken(payload, secret.getBytes());
        return BaseResponse.success("登录成功", jwt);
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
