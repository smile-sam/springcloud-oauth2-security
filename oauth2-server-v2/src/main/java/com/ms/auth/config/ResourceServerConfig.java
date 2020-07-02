package com.ms.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源认证服务器
 * ResourceServerConfig 用于保护oauth相关的endpoints，同时主要作用于用户的登录(form login,Basic auth)
 */
@Configuration
@EnableResourceServer
@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // @Override
    // public void configure(HttpSecurity http) throws Exception {
    //     http
    //             .csrf().disable()
    //             .exceptionHandling()
    //             .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
    //             .and()
    //             .requestMatchers().antMatchers("/api/**")
    //             .and()
    //             .authorizeRequests()
    //             .antMatchers("/api/**").authenticated()
    //             .and()
    //             .httpBasic();
    // }



    // ====== 2020-06-28 start ======
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // http.antMatchers("/oauth/**","/open/**").permitAll() // 不需要认证
        //         .authorizeRequests()
        //         .anyRequest()
        //         .authenticated()
        //         .and()
        //         .requestMatchers()
        //
        //         .antMatchers("/user/**");


        // 配置需要保护的资源路径
        http.authorizeRequests()
                .antMatchers("/v1.1/**","/v1.0/**").authenticated() // 在resource配置需要认证
                .antMatchers("/oauth/**","/open/**").permitAll() // 不需要认证
                .anyRequest().authenticated()
                .and().requestMatchers().antMatchers("/user/**", "/admin/**") // 配置需要保护的资源路径
                .and().csrf().disable();
    }

    // ====== 2020-06-28 end ======
}
