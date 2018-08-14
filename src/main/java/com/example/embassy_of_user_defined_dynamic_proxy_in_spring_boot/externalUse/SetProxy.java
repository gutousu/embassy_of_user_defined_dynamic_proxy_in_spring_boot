package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse;


import java.lang.annotation.*;


/**
 * 代理的类型
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetProxy
{
    Class<? extends AProxy> value();
}
