package com.ms.auth.config;

import com.ms.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 〈OAuth2认证服务器〉
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    // @Autowired
    // @Qualifier("dataSource")
    // private DataSource dataSource;
    //
    // @Autowired
    // private RedisConnectionFactory redisConnectionFactory;

    // @Autowired
    // private MyUserDetailsService myUserDetailsService;

    @Primary
    @Bean
    public TokenStore inMemoryTokenStore() {
        return new InMemoryTokenStore();
    }

    // @Bean
    // public TokenStore jdbcTokenStore() {
    //     return new JdbcTokenStore(dataSource);
    // }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // ======2020-06-28======
        endpoints.tokenStore(inMemoryTokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
        // ======2020-06-28======


        // 配置在数据库中 =======start ==============
        //  endpoints.tokenStore(jdbcTokenStore())
        //          // 配置在内存中
        // // endpoints.tokenStore(tokenStore())
        //          .userDetailsService(userDetailsService)
        //          .authenticationManager(authenticationManager);
        //  endpoints.tokenServices(defaultTokenServices());

        // 配置在数据库中 =======end ==============

        //认证异常翻译
        // endpoints.exceptionTranslator(webResponseExceptionTranslator());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String password = passwordEncoder.encode("123456");
        System.out.println(">>>>>2 password  " + password);
        // ======2020-06-28 start======
        clients.inMemory()
                // 配置client_id
                .withClient("client")
                // 配置client_secret
                .secret(password)
                // 配置访问token的有效期
                .accessTokenValiditySeconds(36000)
                // 配置刷新token的有效期
                .refreshTokenValiditySeconds(864000)
                // 配置redirect_uri,用于授权成功后的跳转
                .redirectUris("http://localhost:6001/admin/hello")
                // 配置申请的权限范围
                .scopes("all")
                // 配置grant_type,表示授权类型
                .authorizedGrantTypes("authorization_code", "password", "refresh_token");

        // ======2020-06-28 end======


        // ======配置在数据库中 start ======
        // ClientDetailsService clientDetailsService = clientDetailsService();
        // ClientDetails clientDetails = clientDetailsService.loadClientByClientId("client");
        // String str = clientDetails.getClientSecret();
        // System.out.println("===================client: " + str);
        // // 配置在数据库中
        // clients.withClientDetails(clientDetailsService());
        // ======配置在数据库中 end ======


        // 配置在内存中
//        clients.inMemory()
//                .withClient("android")
//                .scopes("read")
//                // 使用BCryptPasswordEncoder时需要加密
//                .secret(new BCryptPasswordEncoder().encode("123456"))
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .and()
//                .withClient("webapp")
//                .scopes("read")
//                .authorizedGrantTypes("implicit")
//                .and()
//                .withClient("browser")
//                .authorizedGrantTypes("refresh_token", "password")
//                .scopes("read");
    }

    // @Bean
    // public ClientDetailsService clientDetailsService() {
    //     return new JdbcClientDetailsService(dataSource);
    // }

    // @Bean
    // public WebResponseExceptionTranslator webResponseExceptionTranslator() {
    //     return new MyWebResponseExceptionTranslator();
    // }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     *
     * @return
     */
//     @Primary
//     @Bean
//     public DefaultTokenServices defaultTokenServices() {
//         DefaultTokenServices tokenServices = new DefaultTokenServices();
//         // 配置在数据库中
//         tokenServices.setTokenStore(jdbcTokenStore());
//         // 配置在内存中
// //        tokenServices.setTokenStore(tokenStore());
//         tokenServices.setSupportRefreshToken(true);
//         // token有效期自定义设置，默认12小时
//         tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
//         // refresh_token默认30天
//         tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
//         tokenServices.setClientDetailsService(clientDetailsService());
//         // tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//         // tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30)); // 30天
//         return tokenServices;
//     }


}