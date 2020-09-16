package com.xtkj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtkj.Role;
import com.xtkj.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<Role> userRoles(Integer userId);
}
