package org.lxc.sms.controller;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class StoreIntegration {

    @HystrixCommand(fallbackMethod = "defaultStores")
    public String getStores() {
       throw new RuntimeException();
    }

    public String defaultStores() {
        return "fail back";
    }
}