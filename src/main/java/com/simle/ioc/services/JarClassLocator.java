package com.simle.ioc.services;

import com.simle.ioc.constans.Const;
import com.simle.ioc.exceptions.ClassLocationException;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static com.simle.ioc.constans.Const.JAVA_BINARY_EXT;


public class JarClassLocator implements ClassLocator {
    @Override
    public Set<Class<?>> locate(String path) throws ClassLocationException {
        Set<Class<?>> locatedClasses = new HashSet<>();
        try(JarFile jarFile = new JarFile(path);) {

            while (jarFile.entries().hasMoreElements()){
                JarEntry jarEntry = jarFile.entries().nextElement();
                if(!jarEntry.getName().endsWith(JAVA_BINARY_EXT)){
                    continue;
                }

                String className = jarEntry.getName().replace(JAVA_BINARY_EXT,"")
                        .replaceAll("\\\\",".")
                        .replaceAll("/",".");
                locatedClasses.add(Class.forName(className));

            }

        } catch (IOException | ClassNotFoundException e) {
            throw new ClassLocationException(e.getMessage(),e);
        }


        return locatedClasses;
    }
}
