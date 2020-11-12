package com.simle.ioc.model;

public class EnqueuedServiceDetails {

    private final ServiceDetails<?> serviceDetails;

    private final Class<?> [] dependencies;

    private final Object[] dependencyInstanced;

    public EnqueuedServiceDetails(ServiceDetails<?> serviceDetails) {
        this.serviceDetails = serviceDetails;
        dependencies = serviceDetails.getTargetConstructor().getParameterTypes();
        dependencyInstanced = new Object[dependencies.length];
    }
}
