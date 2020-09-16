package com.xtkj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@TableName("role")
@ToString
public class Role {
    @TableId(value="role_id",type = IdType.AUTO)
    private Integer roleId;
    @TableField("role_name")
    private String roleName;
    @TableField(exist = false)
    private List<Permission> permissions;
}
