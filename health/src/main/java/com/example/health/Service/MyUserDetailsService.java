package com.example.health.Service;

import com.example.health.MyUserBean.MyUserBean;
import com.example.health.mapper.MyUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import  org.springframework.jdbc.core.JdbcTemplate;

 /*UserDetailsService的实现类，用于在程序中引入一个自定义的AuthenticationProvider，实现数据库访问模式的验证*/

@Service
public class MyUserDetailsService implements  UserDetailsService
{  //利用MyUserDetailService实现UserDetailsService 再使用接口UserMapper j对数据库进行查询
    @Autowired
    MyUserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //重写loadUserByUsername 方法获得 userdetails 类型用户
    //实现方法
        MyUserBean userBean = mapper.selectByUsername(username);//通过用户名匹配数据库内容
        if(userBean == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return userBean;//从数据库中返回的信息会组成一个UserDetails接口的实现类的实例
    }




}




