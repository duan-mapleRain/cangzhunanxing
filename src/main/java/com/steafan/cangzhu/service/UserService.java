package com.steafan.cangzhu.service;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.TokenDTO;
import com.steafan.cangzhu.controller.request.user.RegisterDTO;
import com.steafan.cangzhu.controller.request.user.UpdateInfoDTO;
import com.steafan.cangzhu.controller.request.user.UpdatePasswdDTO;
import com.steafan.cangzhu.controller.response.CZResultException;
import com.steafan.cangzhu.controller.response.TokenResponse;
import com.steafan.cangzhu.enums.HttpStatus;
import com.steafan.cangzhu.mapper.UserMapper;
import com.steafan.cangzhu.repository.RedisCache;
import com.steafan.cangzhu.repository.entity.UserDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Setter
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    @Resource
    private UserMapper userMapper;

    private final RedisCache redisCache;

    @Resource
    private final BCryptPasswordEncoder passwordEncoder;

    @Resource
    private final AuthenticationManager authenticationManager;


    private static final String REDIS_KEY_PREFIX_LOGIN = "LOGIN:";
    @Value("${cz-copilot.jwt.secret}")
    private String secret;
    @Value("${cz-copilot.jwt.expire}")
    private int expire;


    //
    public int register(RegisterDTO registerDTO) {

        String encode = passwordEncoder.encode(registerDTO.getPassword());

        QueryWrapper<UserDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", registerDTO.getUsername());
        queryWrapper.eq("email", registerDTO.getEmail());
        if (userMapper.exists(queryWrapper)) {
            throw new CZResultException(HttpStatus.USER_EXIST);
        }
        UserDAO userDAO = new UserDAO();
        userDAO.setUsername(registerDTO.getUsername());
        userDAO.setEmail(registerDTO.getEmail());
        userDAO.setPassword(encode);
        userDAO.setStatus(0);
        if (userMapper.insert(userDAO) == 0) {
            throw new CZResultException(HttpStatus.USER_EXIST);
        }
        return userDAO.getId();
    }


    public void update_passwd(TokenDTO tokenDTO, UpdatePasswdDTO updatePasswdDTO) {
        String username = tokenDTO.getUserDAO().getUsername();
        QueryWrapper<UserDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        if (!passwordEncoder.matches(updatePasswdDTO.getOldPassword(), userDAO.getPassword())) {
            throw new CZResultException(HttpStatus.USER_OLD_PASSWORD_ERROR);
        }
        userDAO.setPassword(passwordEncoder.encode(updatePasswdDTO.getNewPassword()));
        if (userMapper.updateById(userDAO) < 1) {
            throw new CZResultException(HttpStatus.DEVICE_INFO_UPDATE_FAILED);
        }
    }

    public void update_user_info(TokenDTO tokenDTO, UpdateInfoDTO updateInfoDTO) {
    }

    public TokenResponse in(RegisterDTO registerDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(registerDTO.getUsername(), registerDTO.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 若认证失败，给出相应提示
        if (Objects.isNull(authenticate)) {
            throw new CZResultException(HttpStatus.USER_LOGIN_FAIL);
        }

        TokenDTO tokenDTO = (TokenDTO) authenticate.getPrincipal();
        String userId = String.valueOf(tokenDTO.getUserDAO().getUsername());
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

        String cacheKey = REDIS_KEY_PREFIX_LOGIN + userId;
        redisCache.updateCache(cacheKey, TokenDTO.class, tokenDTO, cacheUser -> {
            String cacheToken = cacheUser.getToken();
            if (cacheToken != null && !"".equals(cacheToken)) {
                payload.put("token", cacheToken);
            } else {
                cacheUser.setToken(token);
            }
            return cacheUser;
        }, expire);
        String jwt = JWTUtil.createToken(payload, secret.getBytes());


        TokenResponse rsp = new TokenResponse();
        rsp.setToken(jwt);
        rsp.setValidAfter(LocalDateTime.now().toString());
        rsp.setValidBefore(newTime.toLocalDateTime().toString());
        rsp.setRefreshToken("");
        rsp.setRefreshTokenValidBefore("");
        rsp.setUserInfo(tokenDTO.converter());

        return rsp;
    }


    public void update(TokenDTO tokenDTO) {
        //todo 判断redis中是否存在，如果存在则更新过期时间
    }

}
