package com.steafan.cangzhu.config;


import com.steafan.cangzhu.filter.JwtAuthenticationTokenFilter;
import com.steafan.cangzhu.lmpl.AccessDeniedHandlerImpl;
import com.steafan.cangzhu.lmpl.AuthenticationEntryPointImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SpringBootConfiguration
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 添加放行接口在此处
     */
    private static final String[] URL_WHITELIST = {"/", "/login"};

//    private static final String[] URL_WHITELIST = {"/"};

    private static final String[] URL_SWAGGER_ALL = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**",};

    //添加需要权限1才能访问的接口
    private static final String[] URL_AUTHENTICATION_1 = {"/user/update/passwd"};

    private static final String[] URL_AUTHENTICATION_2 = {};


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(URL_SWAGGER_ALL).requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(URL_WHITELIST).permitAll().antMatchers(URL_AUTHENTICATION_1).hasAnyAuthority("1").antMatchers(URL_AUTHENTICATION_2).hasAnyAuthority("2").anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        http.cors();
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
}
