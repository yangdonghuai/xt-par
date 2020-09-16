package com.xtkj;

import java.util.List;
import java.util.Set;

public interface IJedisClient {
    String get(String key);

    String set(String key, String value);

    void hset(String hkey, String key, String value);

    String hget(String hkey, String key);

    long del(String key);

    void hdel(String hkey, String key);

    Set<String> keys(String k);

    List<User> hgetAllUser(String k);

    List<Role> hgetAllRole(String k);
//
//    List<Permission> hgetAllPermission(String k);
}
