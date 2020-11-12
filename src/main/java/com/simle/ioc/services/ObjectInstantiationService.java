package com.simle.ioc.services;

import com.simle.ioc.exceptions.BeanInstantiationException;
import com.simle.ioc.exceptions.ServiceInstantiationException;
import com.simle.ioc.exceptions.PreDestroyExecutionException;
import com.simle.ioc.model.ServiceDetails;

public interface ObjectInstantiationService {

    void createInstants(ServiceDetails<?> serviceDetails, Object... constructorParams) throws ServiceInstantiationException;

    void createBeanInstance(BeanServiceDetails<?> beanServiceDetails) throws BeanInstantiationException;

    void destroyInstance(ServiceDetails<?> serviceDetails) throws PreDestroyExecutionException;
}
