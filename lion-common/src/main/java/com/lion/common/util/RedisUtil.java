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

import com.lion.common.exception.LionException;
import lombok.SneakyThrows;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * RedisUtil
 * Redis 工具类
 *
 * @author Yanzheng (https://github.com/micyo202)
 * @date 2020/11/25
 */
@Component
public class RedisUtil {

    private RedisUtil() {
    }

    //private static RedisTemplate<String, Object> redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取 redisTemplate 模板
     */
    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 暴露 RedisTemplate 模板
     */
    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置值
     *
     * @param key   Redis键
     * @param value 值
     */
    public static <T> T setValue(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
        return value;
    }

    /**
     * 设置值（带失效时间）
     *
     * @param key     Redis键
     * @param value   值
     * @param seconds 有效时长 (秒)
     */
    public static <T> T setValue(final String key, final T value, final long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 获取值
     *
     * @param key Redis键
     */
    public static Object getValue(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取值
     *
     * @param key   Redis键
     * @param clazz 缓存对应的对象的 class 对象
     */
    public static <T> T getValue(final String key, final Class<T> clazz) {
        return clazz.cast(getValue(key));
    }

    /**
     * 往Hash中存入数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     * @param value   值
     */
    public static <T> T hashPut(final String key, final Object hashKey, final T value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return value;
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key   Redis键
     * @param value Map 对象
     */
    public static Map<Object, Object> hashPutAll(final String key, final Map<Object, Object> value) {
        redisTemplate.opsForHash().putAll(key, value);
        return value;
    }

    /**
     * 获取Hash中的数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     */
    public static Object hashGet(final String key, final Object hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     * @param clazz   缓存对应的对象的 class 对象
     */
    public static <T> T hashGet(final String key, final Object hashKey, final Class<T> clazz) {
        return clazz.cast(hashGet(key, hashKey));
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key      Redis键
     * @param hashKeys Hash键集合
     */
    public static List<Object> hashMultiGet(final String key, final Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 获取Hash中的全部数据
     *
     * @param key Redis键
     */
    public static Map<Object, Object> hashGetAll(final String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key     Redis键
     * @param hashKey Hash键
     */
    public static long hashDeleteKey(final String key, final Object hashKey) {
        return redisTemplate.opsForHash().delete(key, hashKey);
    }

    /**
     * 删除Hash中的多个数据
     *
     * @param key      Redis键
     * @param hashKeys Hash键集合
     */
    public static long hashDeleteKeys(final String key, final Collection<Object> hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     */
    public static long setAdd(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     */
    public static long setDelete(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 获取set中的所有数据
     *
     * @param key Redis键
     */
    public static Set<Object> setGetAll(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 往ZSet中存入数据
     *
     * @param key    Redis键
     * @param values 值
     */
    public static long zsetAdd(final String key, final Set<ZSetOperations.TypedTuple<Object>> values) {
        Long count = redisTemplate.opsForZSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往ZSet中存入数据
     *
     * @param key   Redis键
     * @param value 值
     * @param score 分数
     */
    public static Boolean zsetAdd(final String key, final Object value, final double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 从ZSet中获取range在start到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     */
    public static Set<Object> zsetGet(final String key, final long start, final long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 从ZSet中获取score在min到max之间的元素
     *
     * @param key Redis键
     * @param min 最小分数
     * @param max 最大分数
     */
    public static Set<Object> zsetGet(final String key, final double min, final double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 从ZSet中获取所有元素
     *
     * @param key Redis键
     */
    public static Set<Object> zsetGetAll(final String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    /**
     * 删除ZSet中的数据
     *
     * @param key    Redis键
     * @param values 值
     */
    public static long zsetDelete(final String key, final Set<ZSetOperations.TypedTuple<Object>> values) {
        Long count = redisTemplate.opsForZSet().remove(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     */
    public static long listPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     */
    public static long listPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;

    }

    /**
     * 往List中存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     */
    public static long listPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 从List中获取start到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     */
    public static List<Object> listGet(final String key, final long start, final long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 从List中获取所有元素
     *
     * @param key Redis键
     */
    public static List<Object> listGetAll(final String key) {
        return listGet(key, 0, -1);
    }

    /**
     * 文件操作常量
     */
    private static final String FIELD_FILE_NAME = "fileName";
    private static final String FIELD_FILE_CONTENT = "fileContent";

    /**
     * 保存文件
     * 将文件对象读入内存, 获取字节数组, 最后 Base64 编码
     * 以缓存前缀 + 文件名 作为缓存 key
     *
     * @param key  Redis键
     * @param file 文件对象
     */
    @SneakyThrows
    public static void fileSet(final String key, final File file) {
        final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        Objects.requireNonNull(key, "文件key不能为空");
        // 文件大小，单位：Bytes
        final long fileSize = Objects.requireNonNull(file, "文件对象不能为空").length();
        if (fileSize <= 5 * 1024 * 1024) {
            throw new LionException("上传文件不得超过5MB");
        }
        final String fileName = Objects.requireNonNull(file, "文件对象不能为空").getName();
        final String fileContent = new String(
                Base64.getEncoder().encode(IOUtils.toByteArray(FileUtils.openInputStream(Objects.requireNonNull(file, "文件对象不能为空")))),
                StandardCharsets.UTF_8
        );
        final HashMap<String, String> map = new HashMap<>(4);
        map.put(FIELD_FILE_NAME, fileName);
        map.put(FIELD_FILE_CONTENT, fileContent);
        ops.putAll(key, map);
    }

    /**
     * 获取文件
     *
     * @param key Redis键
     */
    @SneakyThrows
    public static File fileGet(final String key) {
        final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        // 缓存 Key
        final Map<Object, Object> entries = ops.entries(Objects.requireNonNull(key, "文件key不能为空"));
        if (ObjectUtils.isEmpty(entries)) {
            return null;
        }
        final String cachedFileName = MapUtils.getString(entries, FIELD_FILE_NAME);
        final String cachedFileContent = MapUtils.getString(entries, FIELD_FILE_CONTENT);
        final File file = new File(FileUtils.getTempDirectoryPath() + cachedFileName);
        try (
                final FileOutputStream out = new FileOutputStream(file);
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out)
        ) {
            bufferedOutputStream.write(Base64.getDecoder().decode(cachedFileContent));
        }
        return file;
    }

    /**
     * 获取文件的字节数组
     *
     * @param key Redis键
     */
    public static byte[] fileGetBytes(final String key) {
        final HashOperations<String, Object, Object> ops = redisTemplate.opsForHash();
        final Map<Object, Object> entries = ops.entries(Objects.requireNonNull(key, "文件key不能为空"));
        return Base64.getDecoder().decode(MapUtils.getString(entries, FIELD_FILE_CONTENT));
    }

    /**
     * 判断 key 是否存在
     *
     * @param key Redis键
     */
    public static boolean hasKey(final String key) {
        return Optional.ofNullable(redisTemplate.hasKey(key)).orElse(false);
    }

    /**
     * 判断 hash key 是否存在
     *
     * @param key     Redis键
     * @param hashKey Hash键
     */
    public static boolean hasKey(final String key, final Object hashKey) {
        return Optional.ofNullable(redisTemplate.opsForHash().hasKey(key, hashKey)).orElse(false);
    }

    /**
     * 删除
     *
     * @param key Redis键
     */
    public static boolean delete(final String key) {
        return Optional.ofNullable(redisTemplate.delete(key)).orElse(false);
    }

    /**
     * 删除多个key
     *
     * @param keys Redis键集合
     */
    public static long deleteKeys(final Collection<String> keys) {
        return Optional.ofNullable(redisTemplate.delete(keys)).orElse(0L);
    }

    /**
     * 延长指定 key 的过期时间
     *
     * @param key     Redis键
     * @param seconds 有效时长 (秒)
     */
    public static boolean expire(String key, long seconds) {
        return Optional.ofNullable(redisTemplate.expire(key, seconds, TimeUnit.SECONDS)).orElse(false);
    }
}