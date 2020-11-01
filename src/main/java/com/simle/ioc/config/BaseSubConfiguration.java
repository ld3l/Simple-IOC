package com.simle.ioc.config;

import com.simle.ioc.config.configurations.CustomAnnotationConfig;

public abstract class BaseSubConfiguration {
    private final Configuration parent;

    protected BaseSubConfiguration(Configuration config) {
        this.parent = config;
    }

    public Configuration getParent() {
        return parent;
    }


}
