package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy;



import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AProxy;

import java.lang.reflect.Method;


public class ProxyTest extends AProxy
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
