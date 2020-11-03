package com.simle.ioc.testclases;

import com.simle.ioc.annotation.Autowired;
import com.simle.ioc.annotation.PostConstruct;
import com.simle.ioc.annotation.PreDestroy;
import com.simle.ioc.annotation.Service;

@Service
public class TestTwo {
    private final Test test;

    @Autowired
    public TestTwo(Test test) {
        this.test = test;
    }

    @PostConstruct
    public String d(){
        return null;
    }

    @PreDestroy
    public String de(){
        return null;
    }
}
