package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 代理设置接口
 */
interface IAProxy
{
    void setBean(Object bean);
    void setMethodMap(Map<Method, Method> methodMap);
}
