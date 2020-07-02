package com.ms.auth.mapper;

import com.ms.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id , username , password from user where username = #{username} limit 1")
    User loadUserByUsername(@Param("username") String username);
}
