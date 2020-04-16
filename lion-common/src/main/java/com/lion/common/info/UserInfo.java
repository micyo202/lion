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
package com.lion.common.info;

import java.io.Serializable;

/**
 * UserInfo
 * 当前用户的信息
 *
 * @author Yanzheng https://github.com/micyo202
 * @date 2019/10/24
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class UserInfo implements Serializable {

    private final String USER_NAME = System.getProperty("user.name");
    private final String USER_HOME = System.getProperty("user.home");
    private final String USER_DIR = System.getProperty("user.dir");
    private final String USER_LANGUAGE = System.getProperty("user.language");
    private final String USER_COUNTRY = ((System.getProperty("user.country") == null) ? System.getProperty("user.region") : System.getProperty("user.country"));
    private final String JAVA_IO_TMPDIR = System.getProperty("java.io.tmpdir");

    /**
     * 取得当前登录用户的名字
     */
    public final String getName() {
        return USER_NAME;
    }

    /**
     * 取得当前登录用户的home目录
     */
    public final String getHomeDir() {
        return USER_HOME;
    }

    /**
     * 取得当前目录
     */
    public final String getCurrentDir() {
        return USER_DIR;
    }

    /**
     * 取得临时目录
     */
    public final String getTempDir() {
        return JAVA_IO_TMPDIR;
    }

    /**
     * 取得当前登录用户的语言设置
     */
    public final String getLanguage() {
        return USER_LANGUAGE;
    }

    /**
     * 取得当前登录用户的国家或区域设置
     */
    public final String getCountry() {
        return USER_COUNTRY;
    }

    /**
     * 将当前用户的信息转换成字符串。
     */
    @Override
    public final String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("User Name:           ").append(getName())
                .append("\nUser Home Dir:       ").append(getHomeDir())
                .append("\nUser Current Dir:    ").append(getCurrentDir())
                .append("\nUser Temp Dir:       ").append(getTempDir())
                .append("\nUser Language:       ").append(getLanguage())
                .append("\nUser Country:        ").append(getCountry());

        return builder.toString();
    }

}