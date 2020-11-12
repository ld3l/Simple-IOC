package com.simle.ioc.config;

import com.simle.ioc.constans.Const;

public class InstantiationConfig extends BaseSubConfiguration {


    private int MAX_ITERATIONS_COUNT;

    public InstantiationConfig(Configuration config) {
        super(config);
        MAX_ITERATIONS_COUNT = Const.MAX_NUMBER_INSTANTIATION_ITERATIONS;
    }

    public InstantiationConfig setMaximumIterationsCount(int count) {
        this.MAX_ITERATIONS_COUNT = count;
        return this;
    }

    public int getMaximumIterationsCount() {
        return MAX_ITERATIONS_COUNT;
    }
}
