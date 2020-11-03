package com.simle.ioc.services;

import com.simle.ioc.annotation.*;
import com.simle.ioc.config.configurations.CustomAnnotationConfig;
import com.simle.ioc.model.ServiceDetails;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ServiceScanningServices implements ScanningServices {

    private final CustomAnnotationConfig config;

    public ServiceScanningServices(CustomAnnotationConfig config) {
        this.config = config;
        init();
    }

    @Override
    public Set<ServiceDetails<?>> mapServices(Set<Class<?>> locatedClasses) {
        Set<ServiceDetails<?>> serviceDetailsStorage = new HashSet<>();
        Set<Class<? extends Annotation>> annotationsService = config.getAnnotationsService();


        for (Class<?> cls : locatedClasses) {

            if (cls.isInterface()) {
                continue;
            }

            for (Annotation annotation : cls.getAnnotations()) {
                if(annotationsService.contains(annotation.annotationType())){
                    ServiceDetails<?> serviceDetails = new ServiceDetails<>(cls,
                            annotation,
                            findConstructor(cls),
                            findVoidMethodWithZeroParamsAndAnnotations(PostConstruct.class,cls),
                            findVoidMethodWithZeroParamsAndAnnotations(PreDestroy.class,cls),
                            findBeans(cls)
                            );
                    serviceDetailsStorage.add(serviceDetails);
                }
            }

        }
        return serviceDetailsStorage;
    }

    private Constructor<?> findConstructor(Class<?> cls) {

        for (Constructor<?> ctr : cls.getDeclaredConstructors()) {
            if (cls.isAnnotationPresent(Autowired.class)) {
                ctr.setAccessible(true);
                return ctr;
            }
        }
        return cls.getDeclaredConstructors()[0];
    }

    private Method findVoidMethodWithZeroParamsAndAnnotations(Class<? extends Annotation> annotation, Class<?> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getParameterCount() != 0 ||
                    (method.getReturnType() != void.class && method.getReturnType() != Void.class) ||
            !method.isAnnotationPresent(annotation)) {
                continue;
            }

                method.setAccessible(true);
                return method;

        }

        if (cls.getSuperclass() != null) {
            return this.findVoidMethodWithZeroParamsAndAnnotations(annotation, cls.getSuperclass());
        }

        return null;
    }

    private Method[] findBeans(Class<?> cls){
        Set<Class<? extends Annotation>> beanAnnotations = config.getAnnotationsBeanService();
        Set<Method> beanMethods = new HashSet<>();
        for (Method method : cls.getDeclaredMethods()) {
            if(method.getReturnType()==void.class||method.getParameterCount()!=0){
                continue;
            }
            for (Class<? extends Annotation> beanAnnotation : beanAnnotations) {
                if(method.isAnnotationPresent(beanAnnotation)){
                    beanMethods.add(method);
                }
            }
        }
        return beanMethods.toArray(Method[]::new);
    }

    private void init(){
        config.getAnnotationsBeanService().add(Bean.class);
        config.getAnnotationsService().add(Service.class);
    }
}
