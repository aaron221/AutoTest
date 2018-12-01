package com.tester.config;

import lombok.Data;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;


@Data
public class TestConfig {

    //登陆接口uri
    public static String loginUrl;
    //更新用户信息接口uri
    public static String updateUserInfoUrl;
    //获取用户列表接口uri
    public static String getUserListUrl;
    //获取用户信息接口uri
    public static String getUserInfoUrl;
    //添加用户信息接口
    public static String addUserUrl;


    //用来存储cookies信息的变量
    public static CookieStore store;
    //声明http客户端
    public static DefaultHttpClient defaultHttpClient;

}
