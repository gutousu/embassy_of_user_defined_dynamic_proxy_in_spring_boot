package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse;

import java.lang.reflect.Method;


/**
 * 方法代理，可以给不同的方法不同的代理
 */
public abstract class AMethodProxy
{
    public abstract Object run(Object o, Method method, Object[] objects) throws Throwable;
}
