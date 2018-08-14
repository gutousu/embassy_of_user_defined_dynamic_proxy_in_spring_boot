package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.impl;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetMethodProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.MethodProxyTest;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.ProxyTest;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter.ITest1Service;
import org.springframework.stereotype.Service;


@Service
@SetProxy(ProxyTest.class)
public class Test1ServiceImpl extends Test2ServiceImpl implements ITest1Service
{
    @SetMethodProxy(MethodProxyTest.class)
    public String t4()
    {
        System.out.print("");
        return "1test4";
    }
}
