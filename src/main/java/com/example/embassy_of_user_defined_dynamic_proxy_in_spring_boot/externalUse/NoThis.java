package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse;


import java.lang.annotation.*;

/**
 * 如果不想代理某些方法
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoThis
{

}
