package com.example.demo;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {
    public static Set<Class<?>> getClasses(String packageName) throws Exception {
        Set<Class<?>> classes = new HashSet<>();
        String path = packageName.replace(".", "/");
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            if (resource != null) {
                String protocol = resource.getProtocol();
                if ("file".equals(protocol)) {
                    classes.addAll(findClassesByFile(new File(resource.toURI()), packageName));
                }
            }
        }
        return classes;
    }

    private static Set<Class<?>> findClassesByFile(File parent, String packageName) throws Exception {
        Set<Class<?>> classes = new HashSet<>();
        if (!parent.isDirectory()) {
            return classes;
        }
        File[] files = parent.listFiles(file -> file.isDirectory() || file.getName().endsWith(".class"));
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                classes.addAll(findClassesByFile(file, packageName + "." + fileName));
            } else {
                String className = packageName + "." + fileName.substring(0, fileName.length() - 6);
                classes.add(Class.forName(className));
            }
        }
        return classes;
    }
}
