package com.steafan.cangzhu.config;


import com.steafan.cangzhu.filter.JwtAuthenticationTokenFilter;
import com.steafan.cangzhu.lmpl.AccessDeniedHandlerImpl;
import com.steafan.cangzhu.lmpl.AuthenticationEntryPointImpl;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@SpringBootConfiguration
@AllArgsConstructor
@Setter
public class SecurityConfig {

    @Value("${run-type:name}")
    private static String runType = null;

    /**
     * 添加放行接口在此处
     */
    private static final String[] URL_WHITELIST = {"/", "/login","/device/query"};

//    private static final String[] URL_WHITELIST = {"/"};

    private static final String[] URL_SWAGGER_ALL = {"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**",};

    //添加需要权限1才能访问的接口
    private static final String[] URL_AUTHENTICATION_1 = {"/user/update/passwd"};

    private static final String[] URL_AUTHENTICATION_2 = {"/user/add/user"};


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return web -> web.ignoring().antMatchers(URL_SWAGGER_ALL).requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //关闭CSRF,设置无状态连接
        http.csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //允许匿名访问的接口，如果是测试想要方便点就把这段全注释掉
        http.authorizeRequests().antMatchers(URL_WHITELIST).permitAll().antMatchers(URL_AUTHENTICATION_1).hasAnyAuthority("1").antMatchers(URL_AUTHENTICATION_2).hasAnyAuthority("2").anyRequest().authenticated();


        //添加过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器，处理认证失败的JSON响应
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);

        //开启跨域请求
        http.cors();
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();

    }
}
