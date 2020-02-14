package com.sixsixsix516.common;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具类
 *
 * @author sun 2020/2/14 16:32
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }


    /**
     * 根据Class创建一个对象
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 获取所有公共方法
     */
    public static List<Method> getPublicMethods(Class clazz) {
        Method[] declaredMethods = clazz.getDeclaredMethods();
        return Arrays.stream(declaredMethods)
                .filter(method -> Modifier.isPublic(method.getModifiers()))
                .collect(Collectors.toList());
    }

    /**
     * 调动指定方法
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
