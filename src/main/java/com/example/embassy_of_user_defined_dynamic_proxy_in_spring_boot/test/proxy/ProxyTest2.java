package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyTest2 implements InvocationHandler
{

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.print("");
        return null;
    }
}
