package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.NoThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * 添加代理
 */
interface Util
{
    default GenericBeanDefinition setGenericBeanDefinition(Class<?> type, GenericBeanDefinition definition, ApplicationContext applicationContext) throws InstantiationException, IllegalAccessException
    {
        Object proxy = buildProxy(type,applicationContext);
        definition.getPropertyValues().add("type", type);
        definition.getPropertyValues().add("proxy", proxy);
        definition.setBeanClass(FactoryBeanImpl.class);
        return definition;
    }

    /**
     * 创建代理
     * @param type
     * @return
     */
    default Object buildProxy(Class<?> type, ApplicationContext applicationContext) throws InstantiationException, IllegalAccessException
    {
        SetProxy setProxy = type.getAnnotation(SetProxy.class);
        AProxy proxy = (AProxy)setProxy.value().newInstance();
        proxy.setMethodMap(methodMap(type));
        if (!type.isInterface())
        {
            Object bean = applicationContext.getBean(type);
            proxy.setBean(bean);
            return Enhancer.create(type,(IProxyImpl)proxy);
        }
        return Proxy.newProxyInstance(type.getClassLoader(),new Class<?>[]{type},(IProxyInte)proxy);
    }

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
