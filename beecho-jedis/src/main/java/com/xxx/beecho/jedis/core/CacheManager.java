package com.xxx.beecho.jedis.core;

import com.xxx.beecho.jedis.service.RedisCacheService;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/9.
 */
public class CacheManager {
    // 单位为秒
    private final int EXPIRATION = 3600;

    public RedisCacheService redisCacheService;

    public Object getCache(String cacheKey){
        Object obj = new Object();
        try {
            obj = redisCacheService.getCache(cacheKey);
        }catch (Exception e) {
            System.out.println("Cannot get the cache with the cacheKey=" + cacheKey);
        }
        return obj;
    }

    public boolean putCache(String cacheKey,Object objValue){
        return putCache(cacheKey,objValue,EXPIRATION);
    }

    public boolean putCache(String cacheKey,Object objValue,int expiration){
        try {
            if(expiration<=0) expiration = this.EXPIRATION;
            return redisCacheService.putCache(cacheKey,objValue,expiration);
        }catch (Exception e){
            System.out.println("Cannot set the cache with the cacheKey="+cacheKey);
        }
        return false;
    }

    public boolean removeCache(String cacheKey) {
        try {
            redisCacheService.removeCache(cacheKey);
            return true;
        } catch (Exception e) {
            System.out.println("Cannot remove the cache with cacheKey=" + cacheKey);
        }
        return false;
    }

    public boolean putListCache(String cacheKey,Object objValue){
        try {
            return redisCacheService.putListCache(cacheKey,objValue);
        }catch (Exception e) {
            System.out.println("Cannot put the list cache with the cacheKey=" + cacheKey);
        }
        return false;
    }

    public boolean putListCache(String cacheKey, Object objValue, int index) {
        try {
            return redisCacheService.putListCache(cacheKey, objValue, index);
        } catch (Throwable ex) {
        }
        return false;
    }

    public List<Object> getListCache(String cacheKey) {
        try {
            return redisCacheService.getListCache(cacheKey);
        } catch (Throwable ex) {
        }
        return null;
    }

    public List<Object> getListCache(String cacheKey, int start, int end) {
        try {
            return redisCacheService.getListCache(cacheKey, start, end);
        } catch (Throwable ex) {
        }
        return null;
    }

    public boolean trimListCache(String cacheKey, int start, int end) {
        try {
            return redisCacheService.trimListCache(cacheKey, start, end);
        } catch (Throwable ex) {
        }
        return false;
    }

    public boolean putMapCache(String cacheKey, Map map) {
        try {
            return redisCacheService.putMapCache(cacheKey, map);
        } catch (Throwable ex) {
        }
        return false;
    }

    public boolean deleteMapCache(String cacheKey, String mapKey) {
        try {
            return redisCacheService.deleteMapCache(cacheKey, mapKey);
        } catch (Throwable ex) {
        }
        return false;
    }

    public Object getMapValueCache(String cacheKey, String mapKey) {
        try {
            return redisCacheService.deleteMapCache(cacheKey, mapKey);
        } catch (Throwable ex) {
        }
        return false;
    }

    public boolean setKeyExpire(String cacheKey, int expiration) {
        return redisCacheService.setKeyExpire(cacheKey, expiration);
    }

    public boolean exists(String cacheKey){
        return redisCacheService.exists(cacheKey);
    }
}
