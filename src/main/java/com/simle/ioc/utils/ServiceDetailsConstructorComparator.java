package com.simle.ioc.utils;

import com.simle.ioc.model.ServiceDetails;

import java.util.Comparator;

public class ServiceDetailsConstructorComparator implements Comparator<ServiceDetails> {
    @Override
    public int compare(ServiceDetails o1, ServiceDetails o2) {
        return Integer.compare(
                o1.getTargetConstructor().getParameterCount(),
                o2.getTargetConstructor().getParameterCount()
        );
    }
}
