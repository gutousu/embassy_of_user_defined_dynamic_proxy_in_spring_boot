package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.NoThis;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public interface Util
{
    /**
     * 设置可以代理的方法
     * @param type
     * @return
     */
    default Map<Method,Method> methodMap(Class<?> type)
    {
        Map<Method,Method> methodMapIsThis = new HashMap<>();
        Map<Method,Method> methodMap = new HashMap<>();
        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods)
        {
            if (method.getAnnotation(NoThis.class) != null){}
            else if (method.getAnnotation(IsThis.class) != null)
                methodMapIsThis.put(method,method);
            else
                methodMap.put(method,method);
        }
        if (methodMapIsThis.size() > 0)
            return methodMapIsThis;
        return methodMap;
    }
}
