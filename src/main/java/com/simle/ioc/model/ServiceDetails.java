package com.simle.ioc.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceDetails<T> {

    private Class<?> serviceType;

    private Annotation annotation;

    private Constructor<?> targetConstructor;

    private String instanceName;

    private Object instance;

    private Object proxyInstance;

    private Method postConstructMethod;

    private Method preDestroyMethod;

    private Method[] beans;

    private List<ServiceDetails> dependentServices;

    public ServiceDetails() {
        this.dependentServices = new ArrayList<>();
    }

    public Class<?> getServiceType() {
        return serviceType;
    }

    public void setServiceType(Class<?> serviceType) {
        this.serviceType = serviceType;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Constructor<?> getTargetConstructor() {
        return targetConstructor;
    }

    public void setTargetConstructor(Constructor<?> targetConstructor) {
        this.targetConstructor = targetConstructor;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Object getProxyInstance() {
        return proxyInstance;
    }

    public void setProxyInstance(Object proxyInstance) {
        this.proxyInstance = proxyInstance;
    }

    public Method getPostConstructMethod() {
        return postConstructMethod;
    }

    public void setPostConstructMethod(Method postConstructMethod) {
        this.postConstructMethod = postConstructMethod;
    }

    public Method getPreDestroyMethod() {
        return preDestroyMethod;
    }

    public void setPreDestroyMethod(Method preDestroyMethod) {
        this.preDestroyMethod = preDestroyMethod;
    }

    public Method[] getBeans() {
        return beans;
    }

    public void setBeans(Method[] beans) {
        this.beans = beans;
    }

    public List<ServiceDetails> getDependentServices() {
        return Collections.unmodifiableList(dependentServices);
    }

    public void addDependentServices(ServiceDetails<?> dependentServices) {
        this.dependentServices.add(dependentServices);
    }

    public ServiceDetails(Class<?> serviceType, Annotation annotation, Constructor<?> targetConstructor, Method postConstructMethod, Method preDestroyMethod, Method[] beans) {
        this();
        this.serviceType = serviceType;
        this.annotation = annotation;
        this.targetConstructor = targetConstructor;
        this.postConstructMethod = postConstructMethod;
        this.preDestroyMethod = preDestroyMethod;
        this.beans = beans;
    }

    @Override
    public int hashCode(){
        if(serviceType==null){
            return super.hashCode();
        }
        return serviceType.hashCode();
    }
}
