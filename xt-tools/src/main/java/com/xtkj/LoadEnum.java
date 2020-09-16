package com.xtkj;

public enum LoadEnum {
    INSTALL;

    public String getUserClazz(){
        return "com.xtkj.pojo.User";
    }

    public String getRoleClazz(){
        return "com.xtkj.pojo.Role";
    }

    public String getPermissionClazz(){
        return "com.xtkj.pojo.Permission";
    }

}
