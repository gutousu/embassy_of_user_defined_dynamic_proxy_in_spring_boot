package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy;


import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AMethodProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;

import java.lang.reflect.Method;


public class MethodProxyTest extends AMethodProxy
{

    @Override
    public Object run(Object o, Method method, Object[] objects) throws Throwable
    {
        System.out.print("");
        if (method.getDeclaringClass().isInterface())
            return "test";
        Object result = method.invoke(o,objects);
        return result;
    }
}
