package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.contorller;

import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.impl.Test1ServiceImpl;
import com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.test.service.inter.ITest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings(value = {"unchecked","autowire"})
@RestController
@RequestMapping("/test")
public class TestController
{
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired(required = false)
    private ITest1Service testService;

    @Autowired(required = false)
    private Test1ServiceImpl test1ServiceImpl;

    @GetMapping("/t")
    public String t()
    {
        //ITest1Service testService = applicationContext.getBean(ITest1Service.class);
        String result1 = testService.t4();
        test1ServiceImpl.t4();
        return "test1";
    }

    @GetMapping("/ttt")
    public ResponseEntity ttt()
    {
        //return new ResponseEntity<>("内容", HttpStatus.OK);
        return new ResponseEntity<String>("ttt",HttpStatus.NOT_FOUND);
    }
}
