package com.steafan.cangzhu.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.steafan.cangzhu.controller.request.TokenDTO;
import com.steafan.cangzhu.mapper.UserMapper;
import com.steafan.cangzhu.repository.entity.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * security验证密码
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDAO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",username);
        UserDAO userDAO = userMapper.selectOne(queryWrapper);
        if (userDAO == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Integer status = userDAO.getStatus();
        Set<String> authorities = new HashSet<>();

        for (int i = 0; i <= status; i++) {
            authorities.add(Integer.toString(i));
        }
        //数据封装成UserDetails返回
        return new TokenDTO(userDAO, authorities);
    }
}
