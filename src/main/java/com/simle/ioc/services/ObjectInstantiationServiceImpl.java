package com.simle.ioc.services;

import com.simle.ioc.exceptions.BeanInstantiationException;
import com.simle.ioc.exceptions.PostConstructException;
import com.simle.ioc.exceptions.ServiceInstantiationException;
import com.simle.ioc.exceptions.PreDestroyExecutionException;
import com.simle.ioc.model.ServiceDetails;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectInstantiationServiceImpl implements ObjectInstantiationService {
    private static final String INVALID_PARAM_COUNT = "Неверное количество параметров для ";

    @Override
    public void createInstants(ServiceDetails<?> serviceDetails, Object... constructorParams) throws ServiceInstantiationException {
        Constructor<?> targetConstructor = serviceDetails.getTargetConstructor();

        if(constructorParams.length!=targetConstructor.getParameterCount()){
            throw new ServiceInstantiationException(INVALID_PARAM_COUNT + serviceDetails.getServiceType().getName());
        }

        try {
            Object instance = targetConstructor.newInstance(constructorParams);
            serviceDetails.setInstance(instance);
            this.callPostConstruct(serviceDetails);
        } catch (java.lang.InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new ServiceInstantiationException(e.getMessage(),e);
        }
    }

    private void callPostConstruct(ServiceDetails<?> serviceDetails)throws PostConstructException {
        if(serviceDetails.getPostConstructMethod()==null){
            return;
        }

        try {
            serviceDetails.getPostConstructMethod().invoke(serviceDetails.getInstance());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new PostConstructException(e.getMessage(),e);
        }
    }

    @Override
    public void createBeanInstance(BeanServiceDetails<?> beanServiceDetails) throws BeanInstantiationException {
        Method original = beanServiceDetails.getOriginal();
        Object rootInstance = beanServiceDetails.getRoot().getInstance();
        try {
            Object instance = original.invoke(rootInstance);
            beanServiceDetails.setInstance(instance);
        } catch (IllegalAccessException | InvocationTargetException e) {
           throw new BeanInstantiationException(e.getMessage(),e);
        }
    }

    @Override
    public void destroyInstance(ServiceDetails<?> serviceDetails) throws PreDestroyExecutionException {
        if(serviceDetails.getPreDestroyMethod()!=null){
            try {
                serviceDetails.getPreDestroyMethod().invoke(serviceDetails.getInstance());
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new PostConstructException(e.getMessage(),e);
            }
        }
        serviceDetails.setInstance(null);
    }
}
