package com.simle.ioc.services;

import com.simle.ioc.model.Dir;

public interface DirHandler {

    Dir handleDir (Class<?> startClass);

}
