package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.impl;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.NoThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetMethodProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.SetProxy;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.MethodProxyTest;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.proxy.ProxyTest;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter.ITest1Service;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//@Component
@SetProxy(ProxyTest.class)
public class Test1ServiceImpl// implements ITest1Service
{

    @Autowired
    private ITestService testService;

    @SetMethodProxy(MethodProxyTest.class)
    public String t4()
    {
        //Object result = testService.t1();
        System.out.print("");
        return "1test4";
    }
}
