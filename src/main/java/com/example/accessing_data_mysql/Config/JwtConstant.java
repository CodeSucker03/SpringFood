package com.example.accessing_data_mysql.Config;

public class JwtConstant {
    public static final String SECRET_KEY =
    "RandomString@org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.instantiateBean(AbstractAutowireCapableBeanFactory.java:1337)";
    public static final String JWT_HEADER ="Authorization";
    public static final long EXPIRATION_TIME = 60*60*24*1000; // 1 day
    
}
