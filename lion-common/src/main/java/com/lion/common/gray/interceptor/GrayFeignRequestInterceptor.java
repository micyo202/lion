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
package com.lion.common.gray.interceptor;

import com.lion.common.constant.GrayConstant;
import com.lion.common.gray.support.RibbonFilterContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * GrayFeignRequestInterceptor
 * Feign拦截器设置灰度版本号
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/3/19
 */
public class GrayFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 设置请求头header信息
        Enumeration<String> headerNames = request.getHeaderNames();
        if (null != headerNames) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                // 若version版本号为空，则赋值默认版本号
                if (name.equals(GrayConstant.VERSION) && StringUtils.isEmpty(value)) {
                    value = GrayConstant.DEFAULT_VERSION;
                }
                template.header(name, value);
            }
        }

        // 设置灰度版本
        String version = request.getHeader(GrayConstant.VERSION);
        RibbonFilterContextHolder.getCurrentContext().add(GrayConstant.VERSION, version);
    }

}
