package com.simle.ioc;

import com.simle.ioc.config.Configuration;
import com.simle.ioc.enums.DirType;
import com.simle.ioc.model.Dir;
import com.simle.ioc.model.ServiceDetails;
import com.simle.ioc.services.*;

import java.util.Set;

public class SimpleInjector {
    public static void main(String[] args) {
        start(SimpleInjector.class);
    }

    public static void start(Class<?> startClass) {
        start(startClass, new Configuration());

    }

    public static void start(Class<?> startClass, Configuration configuration) {

        ScanningServices services = new ServiceScanningServices(configuration.annotations());

        Dir dir = new SimpleDirHandler().handleDir(startClass);
        ClassLocator locator = new DirClassLocator();
        if(dir.getType()== DirType.JAR){
            locator = new JarClassLocator();
        }
        Set<Class<?>> classes =  locator.locate(dir.getDir());
        Set<ServiceDetails<?>> serviceDetails = services.mapServices(classes);
        System.out.println(serviceDetails);
    }
}
