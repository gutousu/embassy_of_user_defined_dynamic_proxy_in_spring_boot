package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter;


import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.ProxyTest;

@SetProxy(ProxyTest.class)
public interface ITestService extends ITest1Service,Testtttt
{
    @IsThis
    String t1();
    String t2();
    String t3();
}
