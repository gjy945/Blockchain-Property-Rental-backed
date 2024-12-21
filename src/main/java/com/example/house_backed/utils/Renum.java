package com.example.house_backed.utils;

import lombok.Data;

/**
 * @version V1.0
 * @Package com.ss.jwt.R
 * @author: Liu
 * @Date: 10:36
 */
//枚举类
public enum Renum {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(0,"失败"),
    SUCCESS(1,"成功"),
    ;
    private Integer code;
    private String msg;

  Renum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
