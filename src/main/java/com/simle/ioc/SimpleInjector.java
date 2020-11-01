package com.simle.ioc;

import com.simle.ioc.config.Configuration;
import com.simle.ioc.enums.DirType;
import com.simle.ioc.model.Dir;
import com.simle.ioc.services.ClassLocator;
import com.simle.ioc.services.DirClassLocator;
import com.simle.ioc.services.JarClassLocator;
import com.simle.ioc.services.SimpleDirHandler;

public class SimpleInjector {
    public static void main(String[] args) {
        start(SimpleInjector.class);
    }

    public static void start(Class<?> startClass) {
        start(startClass, new Configuration());

    }

    public static void start(Class<?> startClass, Configuration configuration) {
        Dir dir = new SimpleDirHandler().handleDir(startClass);
        ClassLocator locator = new DirClassLocator();
        if(dir.getType()== DirType.JAR){
            locator = new JarClassLocator();
        }
        locator.locate(dir.getDir());
    }
}
