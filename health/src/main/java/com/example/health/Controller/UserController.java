package com.example.health.Controller;
import com.example.health.MyUserBean.MyUserBean;
import com.example.health.mapper.MyUserMapper;
import com.example.health.util.GeneratePassword;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

/**
 * 定义用户相关网址映射的Controller
 */
@Controller
public class UserController {
    @Autowired
    MyUserMapper myUserMapper;

    @RequestMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());

        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "user/user";
    }

    @RequestMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());

        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "admin/admin";
    }

    @RequestMapping("/register")
    public  String AddUser(MyUserBean myUserBean) {

          String temp= myUserBean.getPassword();
        if(temp!=null)//解决用户注册空值异常的报错， 若有空值，则不使用密码保护
        {
            String temp2=GeneratePassword.generatePassword(temp);
            myUserBean.setPassword(temp2);
        }
        else
        myUserBean.setPassword(temp);//这里的temp有可能为空值
        myUserBean.setUsername(myUserBean.getUsername());
        myUserBean.setName(myUserBean.getName());
        myUserBean.setAddress(myUserBean.getAddress());
        myUserBean.setRoles("ROLE_USER");
        myUserBean.setId(myUserBean.getId());
        myUserMapper.insertUser(myUserBean);
        return "register";


    }










}