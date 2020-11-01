package com.simle.ioc.services;

import com.simle.ioc.exceptions.ClassLocationException;

import java.util.Set;

public interface ClassLocator {
    Set<Class<?>> locate(String path)throws ClassLocationException;
}
