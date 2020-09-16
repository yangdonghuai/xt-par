package com.xtkj;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("permission")
@ToString
public class Permission {
    @TableId(value="permission_id",type = IdType.AUTO)
    private Integer permissionId;
    @TableField("permission_name")
    private String permissionName;
}
