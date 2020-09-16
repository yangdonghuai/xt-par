package com.xtkj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xtkj.Permission;
import com.xtkj.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<Permission> rolePermissions(Integer roleId);
}
