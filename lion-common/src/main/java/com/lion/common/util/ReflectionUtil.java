/**
 *   Copyright 2019 Yanzheng (https://github.com/micyo202). All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.lion.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ReflectUtil
 * 反射工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2021/8/2
 */
@Slf4j
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * 循环向上转型，获取对象的DeclaredMethod
     * 使用方法名称、参数个数模糊匹配
     * 如向上转型到Object仍无法找到，返回null
     *
     * @param object         对象
     * @param methodName     方法名
     * @param parameterTypes 参数类型
     */
    public static Method getMethod(final Object object, final String methodName, final Class<?>[] parameterTypes) {

        Method method = getDeclaredMethod(object, methodName, parameterTypes);

        if (null == method) {
            log.warn("对象{}方法{}参数类型{}匹配不到，尝试使用方法名跟参数个数匹配", object, methodName, parameterTypes);
            for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.getName().equals(methodName) && m.getParameters().length == Optional.ofNullable(parameterTypes).orElse(new Class[0]).length) {
                        return m;
                    }
                }
            }
        }

        return method;
    }

    /**
     * 循环向上转型，获取对象的DeclaredMethod
     * 使用方法名称、参数类型精准匹配
     * 如向上转型到Object仍无法找到，返回null
     *
     * @param object         对象
     * @param methodName     方法名
     * @param parameterTypes 参数类型
     */
    public static Method getDeclaredMethod(final Object object, final String methodName, final Class<?>[] parameterTypes) {

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException e) {
                //Method 不在当前类定义, 继续向上转型
            }
        }
        return null;
    }

    /**
     * 执行Getter方法
     *
     * @param object    对象
     * @param fieldName 属性名
     */
    public static Object invokeGetterMethod(final Object object, final String fieldName) {

        String getterMethodName = "get" + StringUtils.capitalize(fieldName);
        return invokeMethod(object, getterMethodName, null);
    }

    /**
     * 执行Setter方法
     *
     * @param object    对象
     * @param fieldName 属性名
     */
    public static void invokeSetterMethod(final Object object, final String fieldName, final Object value) {

        String setterMethodName = "set" + StringUtils.capitalize(fieldName);
        invokeMethod(object, setterMethodName, new Object[]{value});
    }

    /**
     * 执行方法
     *
     * @param object     对象
     * @param methodName 方法名
     * @param args       参数
     */
    public static Object invokeMethod(final Object object, final String methodName, final Object[] args) {

        Class<?>[] parameterTypes = new Class[0];
        if (null != args) {
            int argsLen = args.length;
            parameterTypes = new Class[argsLen];
            for (int i = 0; i < argsLen; i++) {
                parameterTypes[i] = args[i].getClass();
            }
        }

        Method method = getMethod(object, methodName, parameterTypes);
        if (null == method) {
            log.warn("对象{}方法{}不存在", object, methodName);
            return null;
        }

        try {
            return method.invoke(object, args);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("调用对象{}方法{}失败", object, methodName, e);
        }

        return null;
    }

    /**
     * 循环向上转型，获取对象的 DeclaredField
     * 如向上转型到Object仍无法找到，返回null
     *
     * @param object    对象
     * @param fieldName 属性名
     */
    public static Field getField(final Object object, final String fieldName) {

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                //Field 不在当前类定义, 继续向上转型
            }
        }

        return null;
    }

    /**
     * 循环向上转型，获取对象的 DeclaredField
     * 如向上转型到Object仍无法找到，返回null
     *
     * @param object 对象
     */
    public static List<Field> getFields(final Object object) {

        List<Field> fields = new ArrayList<>();
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            Collections.addAll(fields, clazz.getDeclaredFields());
        }

        return fields;
    }

    /**
     * 循环向上转型，获取对象的DeclaredField名称
     * 如向上转型到Object仍无法找到，返回null
     *
     * @param object 对象
     */
    public static List<String> getFieldNames(final Object object) {
        List<Field> fields = getFields(object);
        return fields.stream().map(Field::getName).collect(Collectors.toList());
    }

    /**
     * 读取属性值
     *
     * @param object    对象
     * @param fieldName 属性名
     */
    public static Object getFieldValue(final Object object, final String fieldName) {

        Field field = getField(object, fieldName);

        if (field == null) {
            log.warn("对象{}属性{}不存在", object, fieldName);
            return null;
        }

        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            log.error("对象{}属性{}值获取失败", object, fieldName, e);
        }

        return null;
    }

    /**
     * 设置属性值
     *
     * @param object    对象
     * @param fieldName 属性名
     * @param value     属性值
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {

        Field field = getField(object, fieldName);

        if (field == null) {
            log.warn("对象{}属性{}不存在", object, fieldName);
            return;
        }

        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            log.error("对象{}属性{}值{}设置失败", object, fieldName, value, e);
        }
    }

    /**
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型
     * 如无法找到, 返回Object.class
     * 例：public DemoDao extends BaseDao<Demo, String>
     *
     * @param clazz 类
     * @param index 序号
     */
    public static Class<?> getClassGenricType(final Class<?> clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }

        if (!(params[index] instanceof Class)) {
            return Object.class;
        }

        return (Class<?>) params[index];
    }

    /**
     * 实例化类
     *
     * @param clazz 类
     * @param <T>   返回对象
     */
    public static <T> T newInstance(final Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("实例化类{}失败", clazz, e);
        }
        return null;
    }

}
