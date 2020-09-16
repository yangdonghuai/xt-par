package com.xtkj.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xtkj.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

import java.util.*;

@Component
public class JedisClient implements IJedisClient {

    @Autowired
    private JedisPool pool;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public String get(String key) {
        return pool.getResource().get(key);
    }

    @Override
    public String set(String key, String value) {
        return pool.getResource().set(key, value);
    }

    @Override
    public void hset(String hkey, String key, String value) {
        pool.getResource().hset(hkey, key, value);
    }

    @Override
    public String hget(String hkey, String key) {
        return pool.getResource().hget(hkey, key);
    }

    @Override
    public long del(String key) {
        return pool.getResource().del(key);
    }

    @Override
    public void hdel(String hkey, String key) {
        pool.getResource().hdel(hkey, key);
    }

    @Override
    public Set<String> keys(String k) {
        return pool.getResource().keys(k);
    }

    @Override
    public List<User> hgetAllUser(String k) {
        List<User> list = new ArrayList<>();
        Gson gson = new Gson();
        Collection<String> values = pool.getResource().hgetAll(k).values();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            JsonObject jsonObject = gson.fromJson(iterator.next(), JsonObject.class);
            User user = gson.fromJson(jsonObject, User.class);
            list.add(user);
        }
        if (list.isEmpty()) {
            list = userService.list();
            System.out.println("该数据从数据库中获取，非redis");
        }
        return list;
    }

    @Override
    public List<Role> hgetAllRole(String k) {
        List<Role> list = new ArrayList<>();
        Gson gson = new Gson();
        Collection<String> values = pool.getResource().hgetAll(k).values();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            JsonObject jsonObject = gson.fromJson(iterator.next(), JsonObject.class);
            Role role = gson.fromJson(jsonObject, Role.class);
            list.add(role);
        }
        if (list.isEmpty()) {
            list = roleService.list();
            System.out.println("该数据从数据库中获取，非redis");
        }
        return list;
    }
}
