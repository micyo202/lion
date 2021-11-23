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
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SpringUtil
 * Spting上下文工具类，可通过该类获取容器bean
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2019/08/06
 */
@Component
@Slf4j
public class SpringUtil implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == SpringUtil.applicationContext) {
            SpringUtil.applicationContext = applicationContext;
        }
        log.info("ApplicationContext配置成功，在普通类可以通过调用 SpringUtil.getAppContext() 获取 applicationContext 对象，applicationContext = {}", SpringUtil.applicationContext);
    }

    /**
     * 获取applicationContext
     *
     * @return 应用上下文
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取bean
     *
     * @param name 名称
     * @return bean对象
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取bean
     *
     * @param clazz class类
     * @return bean对象
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及clazz返回指定的bean
     *
     * @param name  名称
     * @param clazz class类
     * @return bean对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 通过类型,返回指定的bean集合
     *
     * @param clazz 类型
     * @param <T>   泛型
     * @return bean对象集合
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return getApplicationContext().getBeansOfType(clazz);
    }

    /**
     * 获取applicationContext
     */
    public static synchronized void clear() {
        log.info("清除applicationContext = {}", SpringUtil.applicationContext);
        SpringUtil.applicationContext = null;
    }

    @Override
    public void destroy() throws Exception {
        SpringUtil.clear();
    }
}
