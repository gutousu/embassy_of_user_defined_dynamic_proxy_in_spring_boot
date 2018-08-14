package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.contorller;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.IsThis;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse.NoThis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test1")
public class Test1Controller
{

    @IsThis
    @GetMapping("/t1")
    public String t1()
    {
        System.out.print("");
        return "test1";
    }
    @NoThis
    @IsThis
    @GetMapping("/t2")
    public String t2()
    {
        System.out.print("");
        return "test2";
    }

    @GetMapping("/t3")
    public String t3()
    {
        System.out.print("");
        return "test3";
    }
}
