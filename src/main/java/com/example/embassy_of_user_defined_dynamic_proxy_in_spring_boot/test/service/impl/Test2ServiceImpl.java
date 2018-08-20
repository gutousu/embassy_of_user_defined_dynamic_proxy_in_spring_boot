package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.impl;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter.ITestService;
import org.springframework.stereotype.Service;


@Service
public class Test2ServiceImpl implements ITestService
{

    @IsThis
    public String t1()
    {
        return "2test1";
    }

    public String t2() {
        return "2test2";
    }

    public String t3() {
        return "2test3";
    }
}
