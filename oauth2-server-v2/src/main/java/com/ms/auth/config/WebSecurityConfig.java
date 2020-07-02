package com.ms.auth.config;


import com.ms.bear.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * 〈security配置〉
 * SecurityConfig 用于保护oauth要开放的资源，同时主要作用于client端以及token的认证(Bearer auth)
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;

    public static void main(String[] args) {
        System.out.println("password " + PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456"));
    }

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // @Bean
    // @Override
    // protected UserDetailsService userDetailsService() {
    //     InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    //     manager.createUser(User.withUsername("admin").password(passwordEncoder().encode("123456")).authorities("ADMIN").build());
    //     return manager;
    //     // return super.userDetailsService();
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //密码验证为了方便我使用了不加密的方式，重写了PasswordEncoder，实际开发还是建议使用BCryptPasswordEncoder
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/asserts/**");
        web.ignoring().antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        // http
        //         .authorizeRequests()
        //         .antMatchers("/oauth/**").permitAll()
        //         .anyRequest().authenticated()
        //         .and()
        //         .csrf().disable();
        // http.authorizeRequests().anyRequest().permitAll();


        // ======2020-06-28 start ======



        http.antMatcher("/oauth/**")
                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/**", "/actuator/", "/token/", "/oauth/authorize").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
        // http.formLogin();
        // http.requestMatchers()
        //         .antMatchers("/oauth/**", "/login/**", "logout/**")
        //         .and()
        //         .authorizeRequests()
        //         .anyRequest()
        //         .authenticated()
        //         .and()
        //         .formLogin()
        //         .permitAll()
        //         .and()
        //         .csrf()
        //         .disable();

        // ======2020-06-28 end ======
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        String password = passwordEncoder().encode("123456");
        System.out.println(">>>>> 1 password\t" + password);
        auth.userDetailsService(userDetailsService);
        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN");
        // auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        // auth.jdbcAuthentication().dataSource(dataSource);
        // auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}