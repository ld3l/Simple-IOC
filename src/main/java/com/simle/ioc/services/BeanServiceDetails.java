package com.simle.ioc.services;

import com.simle.ioc.model.ServiceDetails;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class BeanServiceDetails<T> extends ServiceDetails {

    private final Method original;
    private final ServiceDetails<?> root;

    public BeanServiceDetails(Method original, ServiceDetails<?> root, Class<T> rootType) {
        this.original = original;
        this.root = root;
        this.setServiceType(rootType);
        this.setBeans(new Method[0]);
    }

    public Method getOriginal() {
        return original;
    }

    public ServiceDetails<?> getRoot() {
        return root;
    }
}
