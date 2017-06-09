package com.xxx.beecho.jedis.service;

import com.xxx.beecho.jedis.util.SerializingUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jedis 缓存管理
 *
 * Created by Administrator on 2017/6/9.
 */
public class RedisCacheServiceImpl implements RedisCacheService {

    private static final String JEDIS_SET_RETURN_OK = "OK";

    protected JedisPool jedisPool;

    /**
     * 同步获取 jedis 实例
     *
     * @return
     */
    public synchronized Jedis getJedis() {
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            System.out.println("Get jedis from jedisPool,  error=" + e);
        }
        return jedis;
    }

    public Object getCache(Serializable cacheKey) {
        Jedis jedis = getJedis();

        Object obj = null;
        try {
            obj = SerializingUtil.deserialize(
                    jedis.get(SerializingUtil.serialize(cacheKey))
            );
        } catch (Exception e) {
            System.out.println("getCache, error cacheKey=" + cacheKey + " e=" + e);
        } finally {
            jedis.close();
        }
        return obj;
    }

    public boolean putCache(Serializable cacheKey, Object objValue, int expiration) {
        Jedis jedis = getJedis();

        try {
            String result = jedis.setex(
                    SerializingUtil.serialize(cacheKey),
                    expiration,
                    SerializingUtil.serialize(objValue)
            );

            if (result.equals(JEDIS_SET_RETURN_OK))
                return true;
        } catch (Exception e) {
            System.out.println("putCache, cacheKey=" + cacheKey + ", objValue=" + objValue + ", expiration=" + expiration + ", e=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public void removeCache(Serializable cacheKey) {
        Jedis jedis = getJedis();
        try {
            jedis.del(SerializingUtil.serialize(cacheKey));
        } catch (Exception e) {
            System.out.println("removeCache, cacheKey=" + cacheKey + ", e=" + e);
        } finally {
            jedis.close();
        }
    }

    public boolean putListCache(Serializable cacheKey, Object objValue) {
        Jedis jedis = getJedis();
        try {
            Long num = jedis.rpush(
                    SerializingUtil.serialize(cacheKey),
                    SerializingUtil.serialize(objValue)
            );
            if (num > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("putListCache,  error=" + e);
        } finally {
            jedis.close();
        }

        return false;
    }

    public boolean putListCache(Serializable cacheKey, Object objValue, int index) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.lset(
                    SerializingUtil.serialize(cacheKey),
                    index,
                    SerializingUtil.serialize(objValue));
            if (result.equals(JEDIS_SET_RETURN_OK)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("putListCache, error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public List<Object> getListCache(Serializable cacheKey, int start, int end) {
        Jedis jedis = getJedis();
        try {
            List<byte[]> list = jedis.lrange(
                    SerializingUtil.serialize(cacheKey),
                    start,
                    end);

            if (null != list && list.size() > 0) {
                List<Object> objList = new ArrayList<Object>();
                for (byte[] b : list) {
                    objList.add(SerializingUtil.deserialize(b));
                }
                return objList;
            }
        } catch (Exception e) {
            System.out.println("getListCache, error=" + e);
        } finally {
            jedis.close();
        }
        return null;
    }

    public List<Object> getListCache(Serializable cacheKey) {
        return getListCache(cacheKey, 0, -1);
    }

    public boolean trimListCache(Serializable cacheKey, int start, int end) {
        Jedis jedis = getJedis();
        try {
            String result = jedis.ltrim(
                    SerializingUtil.serialize(cacheKey),
                    start,
                    end);

            if (result.equals(JEDIS_SET_RETURN_OK)) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("trimListCache, error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public boolean putMapCache(Serializable cacheKey, Map<Object, Object> map) {
        Jedis jedis = getJedis();
        try {
            if (null != map && !map.isEmpty()) {
                Map<byte[], byte[]> byteMap = new HashMap<byte[], byte[]>();
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    byteMap.put(
                            SerializingUtil.serialize(entry.getKey()),
                            SerializingUtil.serialize(entry.getValue())
                    );
                }
                String result = jedis.hmset(SerializingUtil.serialize(cacheKey), byteMap);

                if (result.equals(JEDIS_SET_RETURN_OK)) {
                    return true;
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println("putMapCache, error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public boolean deleteMapCache(Serializable cacheKey, Serializable mapKey) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.hdel(
                    SerializingUtil.serialize(cacheKey),
                    SerializingUtil.serialize(mapKey)
            );
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("deleteMapCache, error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public Object getMapValueCache(Serializable cacheKey, Serializable mapKey) {
        Jedis jedis = getJedis();
        try {
            List<byte[]> list = jedis.hmget(
                    SerializingUtil.serialize(cacheKey),
                    SerializingUtil.serialize(mapKey)
            );

            if (null != list && list.size() > 0) {
                return SerializingUtil.deserialize(list.get(0));
            }
        } catch (Exception e) {
            System.out.println("getMapValueCache, error=" + e);
        } finally {
            jedis.close();
        }
        return null;
    }

    public boolean exists(Serializable cacheKey) {
        Jedis jedis = getJedis();
        try {
            return jedis.exists(SerializingUtil.serialize(cacheKey));
        } catch (Exception e) {
            System.out.println("exists, cacheKey=" + cacheKey + ", error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }

    public boolean setKeyExpire(Serializable cacheKey, int expiration) {
        Jedis jedis = getJedis();
        try {
            Long result = jedis.expire(
                    SerializingUtil.serialize(cacheKey),
                    expiration);
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("setKeyExpire, cacheKey=" + cacheKey + ", expiration=" + expiration + ", error=" + e);
        } finally {
            jedis.close();
        }
        return false;
    }
}
