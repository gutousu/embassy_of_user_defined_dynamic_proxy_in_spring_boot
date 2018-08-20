package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.classAndInteOperation;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.IProxyImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;


/**
 * 装配时的处理
 */
class ProxyFactory implements FactoryBean
{
    private Class<?> type;

    private AProxy proxy;

    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setProxy(AProxy proxy) {
        this.proxy = proxy;
    }

    /**
     * 返回代理
     * @return
     * @throws Exception
     */
    public Object getObject() throws Exception
    {
        if (type.isInterface())
            return Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, proxy);
        proxy.setBean(type.newInstance());
        return Enhancer.create(type,proxy);
    }

    public Class<?> getObjectType()
    {
        return type;
    }


    public boolean isSingleton()
    {
        return true;
    }
}
