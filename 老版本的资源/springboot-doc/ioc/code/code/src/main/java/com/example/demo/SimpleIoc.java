package com.example.demo;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleIoc {
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    public void scan(String packageName) throws Exception {
        Set<Class<?>> classes = ClassUtils.getClasses(packageName);
        for (Class<?> cls : classes) {
            if (cls.isAnnotationPresent(Component.class)) {
                register(cls);
            }
        }
    }

    public void register(Class<?> clazz) throws Exception {
        for (Class<?> intf : clazz.getInterfaces()) {
            beanMap.put(intf, createBean(clazz));
        }
    }

    public Object createBean(Class<?> clazz) throws Exception {
        // 如果已经注册了该类，直接返回
        Object bean = beanMap.get(clazz);
        if (bean != null) {
            return bean;
        }
        bean = clazz.newInstance();
        beanMap.put(clazz, bean);
        // 查找该类的带有 @Autowired 注解的属性，并注入依赖
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Class<?> fieldType = field.getType();
                Object dependency = beanMap.get(fieldType);
                if (dependency == null) {
                    dependency = createBean(fieldType);
                }
                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }
        return bean;
    }

    public <T> T getBean(Class<?> clazz) {
        return (T)  beanMap.get(clazz);
    }
}
