package com.simle.ioc.services;

import com.simle.ioc.constans.Const;
import com.simle.ioc.exceptions.ClassLocationException;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static com.simle.ioc.constans.Const.JAVA_BINARY_EXT;

public class DirClassLocator implements ClassLocator {

    private final Set<Class<?>> locatedClasses;
    public static final String INVALID_DIR_MSG = "Ошибка '%s' не является директорией!";

    public DirClassLocator() {
        locatedClasses = new HashSet<>();
    }

    @Override
    public Set<Class<?>> locate(String path) throws ClassLocationException {
        locatedClasses.clear();
        File directory = new File(path);

        if (!directory.isDirectory()){
            throw new ClassLocationException(String.format(INVALID_DIR_MSG,directory));
        }
        try {
            for(File inner:directory.listFiles()){
                scanDir(inner,"");
            }
        } catch (ClassNotFoundException e) {
            throw new ClassLocationException(e.getMessage(),e);
        }
        return locatedClasses;
    }

    private void scanDir(File file, String packageName) throws ClassNotFoundException {
        if(file.isDirectory()){
            packageName += file.getName() + ".";
            for(File inner:file.listFiles()){
                scanDir(inner,packageName);
            }
        }
        else {
            if(file.getName().endsWith(JAVA_BINARY_EXT)){
                String className = packageName + file.getName().replace(JAVA_BINARY_EXT,"");
                this.locatedClasses.add(Class.forName(className));
            }
        }
    }
}
