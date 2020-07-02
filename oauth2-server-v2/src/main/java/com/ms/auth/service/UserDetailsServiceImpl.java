package com.ms.auth.service;

import com.ms.auth.constant.SecurityConstant;
import com.ms.auth.mapper.RoleMapper;
import com.ms.auth.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    // private List<User> userList;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;
    //
    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @PostConstruct
    // public void initData() {

    // String password = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

    // String password = passwordEncoder.encode("123456");
    // userList = new ArrayList<>();
    //
    // userList.add(new User("admin", password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
    // userList.add(new User("andy", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    // userList.add(new User("tom", password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    // }

    private List getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ms.domain.user.User user = userMapper.loadUserByUsername(username);
        if (user != null) {
            log.info("user password is {}", user.getPassword());
            // List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            // List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
            // if (roles != null && !roles.isEmpty()) {
            //     for (Role role : roles) {
            //         authorities.add(new SimpleGrantedAuthority(role.getCode()));
            //         // 线上环境应该通过用户名查询数据库获取加密后的密码
            //     }
            // }
            // String password = passwordEncoder.encode("123456");// "{bcrypt}" +  new BCryptPasswordEncoder().encode("123456");
            // authorities = getAuthority();

            //构建权限
            Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(SecurityConstant.ROLE_NAME_PREFIX + "ADMIN");
            grantedAuthorities.add(grantedAuthority);
            // GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            // grantedAuthorities.add(authority);
            // 可用性 :true:可用 false:不可用
            boolean enabled = true;
            // 过期性 :true:没过期 false:过期
            boolean accountNonExpired = true;
            // 有效性 :true:凭证有效 false:凭证无效
            boolean credentialsNonExpired = true;
            // 锁定性 :true:未锁定 false:已锁定
            boolean accountNonLocked = true;
            // for (Role role : member.getRoles()) {
            //     //角色必须是ROLE_开头，可以在数据库中设置
            //     GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            //     grantedAuthorities.add(grantedAuthority);
            //     //获取权限
            //     for (Permission permission : role.getPermissions()) {
            //         GrantedAuthority authority = new SimpleGrantedAuthority(permission.getUri());
            //         grantedAuthorities.add(authority);
            //     }
            // }
            // 使用BCryptPasswordEncoder时需要加密
            // org.springframework.security.core.userdetails.User u = new User(user.getAccount(), user.getPassword(),
            //         enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);


            org.springframework.security.core.userdetails.User u = new User(user.getUserName(), user.getPassword(), grantedAuthorities);

            return u;
            // return new org.springframework.security.core.userdetails.User(member.getMemberName(), new BCryptPasswordEncoder().encode(member.getPassword()),
            //         enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);

        }


        // List<User> findUserList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        // if (!CollectionUtils.isEmpty(findUserList)) {
        //     return findUserList.get(0);
        // }
        throw new UsernameNotFoundException("username or password is error");
    }

    public static void main(String[] args) {

    }


}
