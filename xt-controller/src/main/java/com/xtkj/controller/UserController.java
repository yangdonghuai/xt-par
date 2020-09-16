package com.xtkj.controller;

import com.xtkj.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private IJedisClient jedisClient;

    @PostMapping("checkLogin")
    public String login(String loginId, String loginPwd, HttpSession httpSession) {
        List<User> usersList = jedisClient.hgetAllUser(LoadEnum.INSTALL.getUserClazz());
        List<Role> rolesList = jedisClient.hgetAllRole(LoadEnum.INSTALL.getRoleClazz());
        for (User user : usersList) {
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                Integer roleId = role.getRoleId();
                for (Role r2 : rolesList){
                    List<Permission> permissions = r2.getPermissions();
                    if (r2.getRoleId() == roleId){
                        role.setPermissions(permissions);
                    }
                }
            }
            System.out.println(user);
            if (user.getLoginId().equals(loginId) && user.getLoginPwd().equals(loginPwd)) {
                httpSession.setAttribute("loginUser", user);
            }
        }
        return "{\"msg\":\"ok\"}";
    }

    @PostMapping("aaa")
    public ModelAndView demo() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("a");
        return modelAndView;
    }

}
