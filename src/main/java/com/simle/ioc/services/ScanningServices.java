package com.simle.ioc.services;

import com.simle.ioc.model.ServiceDetails;

import java.util.Set;

public interface ScanningServices {
    Set<ServiceDetails<?>> mapServices(Set<Class<?>> locatedClasses);
}
