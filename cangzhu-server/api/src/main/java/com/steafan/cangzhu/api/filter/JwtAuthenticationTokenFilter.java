package com.steafan.cangzhu.api.filter;

import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.steafan.cangzhu.api.config.security.CzProperties;
import com.steafan.cangzhu.api.controller.request.TokenDTO;
import com.steafan.cangzhu.api.repository.RedisCache;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author AnselYuki
 */
@Setter
@Component
@RequiredArgsConstructor
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final RedisCache redisCache;

    private final CzProperties properties;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws IOException, ServletException {
        try {
            var token = extractToken(request);
            var jwt = parseAndValidateJwt(token);
            var user = retrieveAndValidateUser(jwt);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException ex) {
            logger.trace(ex.getMessage());
        } catch (Exception ignored) {
        } finally {
            filterChain.doFilter(request, response);
        }
    }

    @NotNull
    private String extractToken(HttpServletRequest request) throws Exception {
        if (SecurityContextHolder.getContext().getAuthentication() != null) throw new Exception("no need to auth");
        var head = request.getHeader(properties.getJwt().getHeader());
        if (head == null || !head.startsWith("Bearer ")) throw new Exception("token not found");
        return head.substring(7);
    }

    @NotNull
    private JWT parseAndValidateJwt(String token) throws BadCredentialsException {
        if (!JWTUtil.verify(token, properties.getJwt().getSecret().getBytes()))
            throw new BadCredentialsException("invalid token");
        var jwt = JWTUtil.parseToken(token);
        var now = DateTime.now();
        var notBefore = DateTime.of((Long) jwt.getPayload(RegisteredPayload.NOT_BEFORE));
        if (now.isBefore(notBefore)) throw new CredentialsExpiredException("haven't come into effect");
        var expiresAt = DateTime.of((Long) jwt.getPayload(RegisteredPayload.EXPIRES_AT));
        if (now.isAfter(expiresAt)) throw new CredentialsExpiredException("token expired");
        return jwt;
    }

    @NotNull
    private TokenDTO retrieveAndValidateUser(JWT jwt) throws AuthenticationException {
        var redisKey = "LOGIN:" + jwt.getPayload("userId");
        var user = redisCache.getCache(redisKey, TokenDTO.class);
        if (user == null) throw new UsernameNotFoundException("user not found");
        var jwtToken = jwt.getPayload("token").toString();
        if (!Objects.equals(user.getToken(), jwtToken)) throw new BadCredentialsException("invalid token");
        return user;
    }
}
