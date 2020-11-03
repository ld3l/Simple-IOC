package com.simle.ioc.testclases;

import com.simle.ioc.annotation.Bean;
import com.simle.ioc.annotation.Service;

@Service
public class Test {
    @Bean
    public TestBean get(){
        return new TestBean();
    }
}
