package com.javaclimb.houserent.common.dto;

import lombok.Data;

//前后端传输Json格式的数据
@Data
public class JsonResult {
    //返回的状态码，0是失败 1为成功
    private Integer code;
    //返回的信息
    private String msg;
    //返回的数据
    private Object result;

    //返回数据的构造方法
    public JsonResult(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    //不返回数据
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //不返回信息
    public JsonResult(Integer code, Object result) {
        this.code = code;
        this.result = result;
    }

    //成功返回
    public static JsonResult success() {
        return new JsonResult(1, "操作成功");
    }

    public static JsonResult success(String msg) {
        return new JsonResult(1, msg);
    }

    public static JsonResult success(String msg, Object result) {
        return new JsonResult(1, msg, result);
    }

    //错误返回
    public static JsonResult error(String msg) {
        return new JsonResult(0, msg);
    }

    public static JsonResult error(String msg, Object result) {
        return new JsonResult(0, msg, result);
    }
}
