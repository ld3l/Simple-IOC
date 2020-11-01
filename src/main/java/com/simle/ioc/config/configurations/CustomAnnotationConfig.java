package com.simle.ioc.config.configurations;

import com.simle.ioc.config.BaseSubConfiguration;
import com.simle.ioc.config.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CustomAnnotationConfig extends BaseSubConfiguration {

    private final Set<Class<? extends Annotation>> annotationsService;
    private final Set<Class<? extends Annotation>> annotationsBeanService;

    public CustomAnnotationConfig(Configuration config) {
        super(config);
        annotationsService = new HashSet<>();
        annotationsBeanService = new HashSet<>();
    }

    public CustomAnnotationConfig addAnnotationConfig(Class<? extends Annotation> annotation){
        annotationsService.add(annotation);
        return this;
    }
    public CustomAnnotationConfig addAnnotationConfigs(Class<? extends Annotation>... annotation){
        annotationsService.addAll(Arrays.asList(annotation));
        return this;
    }

    public CustomAnnotationConfig addBeanAnnotation(Class<? extends Annotation> annotation){
        annotationsBeanService.add(annotation);
        return this;
    }
    public CustomAnnotationConfig addBeanAnnotations(Class<? extends Annotation>... annotation){
        annotationsBeanService.addAll(Arrays.asList(annotation));
        return this;
    }

    public Set<Class<? extends Annotation>> getAnnotationsService() {
        return annotationsService;
    }

    public Set<Class<? extends Annotation>> getAnnotationsBeanService() {
        return annotationsBeanService;
    }
}
