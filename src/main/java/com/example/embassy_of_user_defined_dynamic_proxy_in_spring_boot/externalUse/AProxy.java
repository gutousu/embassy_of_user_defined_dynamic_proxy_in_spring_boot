package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.IProxyImpl;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.IProxyInte;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * 代理抽象类
 */
public abstract class AProxy implements IProxyImpl,IProxyInte
{
    private Object bean;
    private Map<Method,Method> methodMap;

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public void setMethodMap(Map<Method, Method> methodMap) {
        this.methodMap = methodMap;
    }

    /**
     * 类代理中转站
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable
    {
        if (methodMap != null && methodMap.size() > 0 && methodMap.get(method) != null)
            return runBefore(bean,method,objects);
        return method.invoke(bean,objects);
    }

    /**
     * 接口代理中转站
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable
    {
        if (methodMap != null && methodMap.size() > 0 && methodMap.get(method) != null)
            return runBefore(bean,method,objects);
        return null;
    }

    /**
     * 执行run方法之前的处理
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    private Object runBefore(Object o,Method method, Object[] objects) throws Throwable
    {
        SetMethodProxy setMethodProxy = method.getDeclaredAnnotation(SetMethodProxy.class);
        if (setMethodProxy == null)
            return run(o,method,objects);
        AMethodProxy methodProxy = setMethodProxy.value().newInstance();
        return methodProxy.run(o,method,objects);
    }

    /**
     * 重写此方法实现代理
     * @param o
     * @param method
     * @param objects
     * @return
     * @throws Throwable
     */
    public abstract Object run(Object o,Method method, Object[] objects) throws Throwable;
}
