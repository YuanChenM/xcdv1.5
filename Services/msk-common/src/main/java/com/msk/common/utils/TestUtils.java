package com.msk.common.utils;

import com.hoperun.core.exception.SystemException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;

/**
 * Created by jackjiang on 16/6/30.
 */
public final class TestUtils {

    public static URLClassLoader getClassLoader(){
        URLClassLoader classLoader = null;
        try {
            classLoader = getClassLoad("target/classes");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return classLoader;
    }

    public static URLClassLoader getTestClassLoad()  {
        URLClassLoader testClassLoader = null;
        try {
            testClassLoader = getClassLoad("src/test");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return testClassLoader;
    }

    private static URLClassLoader getClassLoad(String classPatch) throws MalformedURLException {
        URLClassLoader classLoader = new URLClassLoader(new URL[] { new File(classPatch).toURI().toURL() }, null) {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return ClassLoader.getSystemClassLoader().loadClass(name);
            }
        };
        return classLoader;
    }

    public static String getTestResource(){
        URLClassLoader testClassLoader = getTestClassLoad();
        String resourcesPath = testClassLoader.getResource("resources").getPath();
        //将路径空格转译为%20
        resourcesPath = resourcesPath.replaceAll("%20", " ");
        return resourcesPath;
    }
    public static String getRestPackagePatch(String packageName){
        URLClassLoader classLoader = getClassLoader();
        String packagePath = packageName.replace(".", "/");
        packagePath = classLoader.getResource(packagePath).getPath();
        //将路径空格转译为%20
        packagePath = packagePath.replaceAll("%20", " ");
        return packagePath;
    }

    public static Collection<File> getClassFileList(File packageFileDir){
        Collection<File> files = org.apache.commons.io.FileUtils.listFiles(packageFileDir,null,false);
        return files;
    }

    public static Class<?> loadClass(URLClassLoader classLoader,String className){
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new SystemException();
        }
    }
}
