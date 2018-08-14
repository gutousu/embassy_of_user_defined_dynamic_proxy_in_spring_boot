package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import org.springframework.beans.factory.FactoryBean;


/**
 * bean装配时的处理
 * @param <T>
 */
class FactoryBeanImpl<T> implements FactoryBean<T>
{
    private Class<T> type;

    private Object proxy;

    public void setType(Class<T> type) {
        this.type = type;
    }

    public void setProxy(Object proxy) {
        this.proxy = proxy;
    }

    /**
     * 返回代理
     * @return
     * @throws Exception
     */
    public T getObject() throws Exception
    {
        return (T)proxy;
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
