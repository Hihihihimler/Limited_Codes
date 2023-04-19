package com.javaclimb.houserent.common.constant;

//常量类
public class Constant {
    //用户SESSION的key
    public static final String SESSION_USER_KEY = "user";
    //相对目录
    public static final String UPLOAD_ABSOLUTE_PATH = "/upload/";
    //上传目录
    public static final String UPLOAD_PATH = System.getProperties().getProperty("user.home")+UPLOAD_ABSOLUTE_PATH;
    //图片session前缀
    public static final String SESSION_IMG_PREFIX = "SESSION_IMG_";

}
