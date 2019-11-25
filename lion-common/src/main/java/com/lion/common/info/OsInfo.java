package com.lion.common.info;

import java.io.Serializable;

/**
 * OsInfo
 * 当前OS的信息
 *
 * @author Yanzheng
 * @date 2019/10/24
 * Copyright 2019 Yanzheng. All rights reserved.
 */
public class OsInfo implements Serializable {

    private final String OS_VERSION = System.getProperty("os.version");
    private final String OS_ARCH = System.getProperty("os.arch");
    private final String OS_NAME = System.getProperty("os.name");
    private final boolean IS_OS_AIX = getOSMatches("AIX");
    private final boolean IS_OS_HP_UX = getOSMatches("HP-UX");
    private final boolean IS_OS_IRIX = getOSMatches("Irix");
    private final boolean IS_OS_LINUX = getOSMatches("Linux") || getOSMatches("LINUX");
    private final boolean IS_OS_MAC = getOSMatches("Mac");
    private final boolean IS_OS_MAC_OSX = getOSMatches("Mac OS X");
    private final boolean IS_OS_OS2 = getOSMatches("OS/2");
    private final boolean IS_OS_SOLARIS = getOSMatches("Solaris");
    private final boolean IS_OS_SUN_OS = getOSMatches("SunOS");
    private final boolean IS_OS_WINDOWS = getOSMatches("Windows");
    private final boolean IS_OS_WINDOWS_2000 = getOSMatches("Windows", "5.0");
    private final boolean IS_OS_WINDOWS_95 = getOSMatches("Windows 9", "4.0");
    private final boolean IS_OS_WINDOWS_98 = getOSMatches("Windows 9", "4.1");
    private final boolean IS_OS_WINDOWS_ME = getOSMatches("Windows", "4.9");
    private final boolean IS_OS_WINDOWS_NT = getOSMatches("Windows NT");
    private final boolean IS_OS_WINDOWS_XP = getOSMatches("Windows", "5.1");

    private final boolean IS_OS_WINDOWS_7 = getOSMatches("Windows", "6.1");
    private final boolean IS_OS_WINDOWS_8 = getOSMatches("Windows", "6.2");
    private final boolean IS_OS_WINDOWS_8_1 = getOSMatches("Windows", "6.3");
    private final boolean IS_OS_WINDOWS_10 = getOSMatches("Windows", "10.0");

    // 由于改变file.encoding属性并不会改变系统字符编码，为了保持一致，通过LocaleUtil取系统默认编码。
    private final String FILE_SEPARATOR = System.getProperty("file.separator");
    private final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final String PATH_SEPARATOR = System.getProperty("path.separator");

    /**
     * 取得当前OS的架构
     */
    public final String getArch() {
        return OS_ARCH;
    }

    /**
     * 取得当前OS的名称
     */
    public final String getName() {
        return OS_NAME;
    }

    /**
     * 取得当前OS的版本
     */
    public final String getVersion() {
        return OS_VERSION;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isAix() {
        return IS_OS_AIX;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isHpUx() {
        return IS_OS_HP_UX;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isIrix() {
        return IS_OS_IRIX;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isLinux() {
        return IS_OS_LINUX;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isMac() {
        return IS_OS_MAC;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isMacOsX() {
        return IS_OS_MAC_OSX;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isOs2() {
        return IS_OS_OS2;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isSolaris() {
        return IS_OS_SOLARIS;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isSunOS() {
        return IS_OS_SUN_OS;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows() {
        return IS_OS_WINDOWS;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows2000() {
        return IS_OS_WINDOWS_2000;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows95() {
        return IS_OS_WINDOWS_95;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows98() {
        return IS_OS_WINDOWS_98;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindowsME() {
        return IS_OS_WINDOWS_ME;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindowsNT() {
        return IS_OS_WINDOWS_NT;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindowsXP() {
        return IS_OS_WINDOWS_XP;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows7() {
        return IS_OS_WINDOWS_7;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindoows8() {
        return IS_OS_WINDOWS_8;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows8_1() {
        return IS_OS_WINDOWS_8_1;
    }

    /**
     * 判断当前OS的类型
     */
    public final boolean isWindows10() {
        return IS_OS_WINDOWS_10;
    }

    /**
     * 匹配OS名称
     */
    private boolean getOSMatches(String osNamePrefix) {
        if (OS_NAME == null) {
            return false;
        }

        return OS_NAME.startsWith(osNamePrefix);
    }

    /**
     * 匹配OS名称
     */
    private boolean getOSMatches(String osNamePrefix, String osVersionPrefix) {
        if ((OS_NAME == null) || (OS_VERSION == null)) {
            return false;
        }

        return OS_NAME.startsWith(osNamePrefix) && OS_VERSION.startsWith(osVersionPrefix);
    }

    /**
     * 取得OS的文件路径的分隔符
     */
    public final String getFileSeparator() {
        return FILE_SEPARATOR;
    }

    /**
     * 取得OS的文本文件换行符
     */
    public final String getLineSeparator() {
        return LINE_SEPARATOR;
    }

    /**
     * 取得OS的搜索路径分隔符
     */
    public final String getPathSeparator() {
        return PATH_SEPARATOR;
    }

    /**
     * 将OS的信息转换成字符串
     */
    @Override
    public final String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("OS Arch:         ").append(getArch())
                .append("\nOS Name:         ").append(getName())
                .append("\nOS Version:      ").append(getVersion());

        return builder.toString();
    }

}