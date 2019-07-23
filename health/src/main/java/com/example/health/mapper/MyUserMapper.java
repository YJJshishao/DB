package com.example.health.mapper;

import com.example.health.MyUserBean.MyUserBean;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * Created by wxb on 2018/10/23 0023.
 * 数据库表user的mapper类
 */
@Mapper
@Component
public interface MyUserMapper {
    /**
     * 从数据库中查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")//通过用户名进行查询
    MyUserBean selectByUsername(@Param("username") String username);

    //向用户数据库中插入数据
    @Options(useGeneratedKeys = true,keyProperty = "id")//自增主键Id
    @Insert("insert into user(id,name,address,username,password,roles)values(#{id},#{name},#{address},#{username},#{password},#{roles})")
    public void insertUser(MyUserBean myUserBean);



}



