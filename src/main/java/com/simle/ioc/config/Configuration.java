package com.simle.ioc.config;

import com.simle.ioc.config.configurations.CustomAnnotationConfig;

public class Configuration {
    private final CustomAnnotationConfig annotations;

    public Configuration() {
        annotations = new CustomAnnotationConfig(this);
    }

    public CustomAnnotationConfig annotations(){
        return this.annotations;
    }

    public Configuration build(){
        return this;
    }
}
