package com.example.embassy_of_user_defined_dynamic_proxy_in_spring_boot.externalUse;


import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetMethodProxy
{
    Class<? extends AMethodProxy> value();
}
