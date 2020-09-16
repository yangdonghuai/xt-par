package com.xtkj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@TableName("users")
@ToString
public class User {
    @TableId(value="user_id",type = IdType.AUTO)
    private Integer userId;
    @TableField("user_name")
    private String userName;
    @TableField("login_id")
    private String loginId;
    @TableField("login_password")
    private String loginPwd;
    @TableField(exist = false)
    private List<Role> roles;
}
