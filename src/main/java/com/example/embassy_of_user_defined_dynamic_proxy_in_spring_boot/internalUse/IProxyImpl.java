package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.internalUse;

import org.springframework.cglib.proxy.MethodInterceptor;


/**
 * 类代理
 */
public interface IProxyImpl extends IAProxy,MethodInterceptor
{

}
