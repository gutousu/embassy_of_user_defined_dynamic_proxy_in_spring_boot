package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter;


import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.ProxyTest;

@SetProxy(ProxyTest.class)
public interface ITest1Service
{
    String t4();
}
