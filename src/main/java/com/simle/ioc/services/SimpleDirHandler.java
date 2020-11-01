package com.simle.ioc.services;

import com.simle.ioc.enums.DirType;
import com.simle.ioc.model.Dir;

import java.io.File;

public class SimpleDirHandler implements DirHandler {
    private static final String JAR_EXTENSION = ".jar";
    @Override
    public Dir handleDir(Class<?> startClass) {
        String path = getDir(startClass);
        return new Dir(path, getDirType(path));
    }

    private String getDir(Class<?> startClass) {
        return startClass.getProtectionDomain().getCodeSource().getLocation().getFile();
    }

    private DirType getDirType(String dir) {
        File file = new File(dir);
        return file.isDirectory() && dir.endsWith(JAR_EXTENSION) ? DirType.JAR : DirType.DIR;
    }
}
