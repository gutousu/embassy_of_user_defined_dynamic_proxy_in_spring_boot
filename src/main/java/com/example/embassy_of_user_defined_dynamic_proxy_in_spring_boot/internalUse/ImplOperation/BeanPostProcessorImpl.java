package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.ImplOperation;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.AProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.IProxyImpl;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse.Util;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;


@Component
public class BeanPostProcessorImpl implements BeanPostProcessor,Util
{
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return beanOperation(bean);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 为bean添加代理
     * @param bean
     * @return
     */
    private Object beanOperation(Object bean)
    {
        Class<?> type = bean.getClass();
        SetProxy setProxy = type.getAnnotation(SetProxy.class);
        if (setProxy == null)
            return bean;
        try {
            AProxy proxy = setProxy.value().newInstance();
            proxy.setBean(bean);
            proxy.setMethodMap(methodMap(type));
            bean = Enhancer.create(type,(IProxyImpl)proxy);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return bean;
    }
}
