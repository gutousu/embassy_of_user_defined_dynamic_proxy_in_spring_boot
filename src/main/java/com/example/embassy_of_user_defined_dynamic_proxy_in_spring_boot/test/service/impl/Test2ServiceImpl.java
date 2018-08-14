package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.impl;

import org.springframework.stereotype.Service;


@Service
public class Test2ServiceImpl// implements ITestService
{

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

    public String t4() {
        return null;
    }
}
