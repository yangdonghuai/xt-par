package com.xtkj.interceptor;

import com.xtkj.Permission;
import com.xtkj.Role;
import com.xtkj.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.List;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        PrintWriter out = response.getWriter();
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            out.println("{\"msg\":\"认证失败\"}");
            return false;
        }

        if (user != null) {
            User u = (User) user;
            List<Role> roles = u.getRoles();
            for (int i = 0; i < roles.size(); i++) {
                List<Permission> permissions = roles.get(i).getPermissions();
                for (int j = 0; j < permissions.size(); j++) {
                    String url = permissions.get(j).getPermissionName();
                    if (request.getRequestURL().toString().contains(url)) {
                        return true;
                    }
                }
            }
        }

        out.println("当前用户没有访问该页面的权限");
        return false;
    }

    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
