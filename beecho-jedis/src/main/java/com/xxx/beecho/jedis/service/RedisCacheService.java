package com.xxx.beecho.jedis.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 缓存管理接口
 *
 * Created by Administrator on 2017/6/9.
 */
public interface RedisCacheService {

    /**
     * 根据缓存key获取值
     *
     * @param cacheKey
     * @return
     */
    public Object getCache(Serializable cacheKey);

    /**
     * 设置缓存值及失效时间，单位为秒
     *
     * @param cacheKey
     * @param objValue
     * @param expiration
     * @return
     */
    public boolean putCache(
            Serializable cacheKey,
            Object objValue,
            int expiration);

    /**
     * 清楚指定缓存
     *
     * @param cacheKey
     */
    public void removeCache(Serializable cacheKey);

    /**
     * 向指定集合中添加对象，在 list 尾部添加元素
     *
     * @param cacheKey
     * @param objValue
     * @return
     */
    public boolean putListCache(Serializable cacheKey,
                                Object objValue);

    /**
     * 向指定集合中插入对象，指定索引
     *
     * @param cacheKey
     * @param objValue
     * @param index
     * @return
     */
    public boolean putListCache(Serializable cacheKey,
                                Object objValue,
                                int index);


    /**
     * 根据索引，返回指定集合段
     *
     * @param cacheKey
     * @param start
     * @param end
     * @return
     */
    public List<Object> getListCache(Serializable cacheKey,
                                     int start,
                                     int end);

    /**
     * 返回指定集合
     *
     * @param cacheKey
     * @return
     */
    public List<Object> getListCache(Serializable cacheKey);

    /**
     * 剪裁 list 集合
     *
     * @param cacheKey
     * @param start
     * @param end
     * @return
     */
    public boolean trimListCache(Serializable cacheKey,
                                 int start,
                                 int end);

    /**
     * 添加 map 集合
     *
     * @param cacheKey
     * @param map
     * @return
     */
    public boolean putMapCache(Serializable cacheKey,
                               Map<Object,Object> map);


    /**
     * 删除 map 中的键值
     *
     * @param cacheKey
     * @param mapKey
     * @return
     */
    public boolean deleteMapCache(Serializable cacheKey,
                                  Serializable mapKey);

    /**
     * 获取 map 中的值
     *
     * @param cacheKey
     * @param mapKey
     * @return
     */
    public Object getMapValueCache(Serializable cacheKey,
                                   Serializable mapKey);

    /**
     * 缓存 key 是否存在
     *
     * @param cacheKey
     * @return
     */
    public boolean exists(Serializable cacheKey);

    /**
     * 设置 key 失效时间
     *
     * @param cacheKey
     * @param expiration
     * @return
     */
    public boolean setKeyExpire(Serializable cacheKey,
                                int expiration);
}
