package com.xtkj.config;

import com.google.gson.Gson;
import com.xtkj.*;
import com.xtkj.dao.RoleMapper;
import com.xtkj.dao.UserMapper;
import com.xtkj.impl.JedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class LoadConfig {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private JedisClient jedisClient;

    @PostConstruct
    public void load() {
        new Thread(() -> {
            log.debug("---------------启动线程准备缓存数据----------------");
            log.debug("---------------删除 之前数据----------------");
            Set<String> userKeys = jedisClient.keys(LoadEnum.INSTALL.getUserClazz() + "*");
            Set<String> roleKeys = jedisClient.keys(LoadEnum.INSTALL.getRoleClazz() + "*");
            Set<String> permissionKeys = jedisClient.keys(LoadEnum.INSTALL.getPermissionClazz() + "*");
            Iterator<String> iterator1 = userKeys.iterator();
            Iterator<String> iterator2 = roleKeys.iterator();
            Iterator<String> iterator3 = permissionKeys.iterator();
            while (iterator1.hasNext()) {
                String key = iterator1.next();
                jedisClient.del(key);
                log.debug(key + "---------------已被删除 ----------------");
            }
            while (iterator2.hasNext()) {
                String key = iterator2.next();
                jedisClient.del(key);
                log.debug(key + "---------------已被删除 ----------------");
            }
            while (iterator3.hasNext()) {
                String key = iterator3.next();
                jedisClient.del(key);
                log.debug(key + "---------------已被删除 ----------------");
            }
            log.debug("---------------删除完毕开始缓存----------------");
            List<User> userList = userService.list();
            List<Role> roleList = roleService.list();
            List<Permission> permissionList = permissionService.list();
            Gson gson = new Gson();
            for (User user : userList) {
                List<Role> roles = userMapper.userRoles(user.getUserId());
                user.setRoles(roles);
                jedisClient.hset(LoadEnum.INSTALL.getUserClazz(), user.getUserId().toString(), gson.toJson(user));
            }
            for (Role role : roleList) {
                List<Permission> permissions = roleMapper.rolePermissions(role.getRoleId());
                role.setPermissions(permissions);
                jedisClient.hset(LoadEnum.INSTALL.getRoleClazz(), role.getRoleId().toString(), gson.toJson(role));
            }
            for (Permission permission : permissionList) {
                jedisClient.hset(LoadEnum.INSTALL.getPermissionClazz(), permission.getPermissionId().toString(), gson.toJson(permission));
            }
            log.debug("---------------缓存完毕----------------");
        }).start();
    }


}
