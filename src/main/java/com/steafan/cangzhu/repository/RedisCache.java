package com.steafan.cangzhu.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Setter
@Component
@RequiredArgsConstructor
public class RedisCache {

    private final StringRedisTemplate redisTemplate;

    public <T> void setCache(final String key, T value, long timeout, TimeUnit timeUnit) {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return;
        }
        if (timeout <= 0) {
            redisTemplate.opsForValue().set(key, json);
        } else {
            redisTemplate.opsForValue().set(key, json, timeout, timeUnit);
        }
    }

    public <T> T getCache(final String key, Class<T> valueType, Supplier<T> onMiss, long timeout, TimeUnit timeUnit) {
        T result;
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(json)) {
                if (onMiss != null) {
                    //上锁
                    synchronized (RedisCache.class) {
                        //再次查询缓存，目的是判断是否前面的线程已经set过了
                        json = redisTemplate.opsForValue().get(key);
                        //第二次校验缓存是否存在
                        if (StringUtils.isEmpty(json)) {
                            result = onMiss.get();
                            //数据库中不存在
                            if (result == null) {
                                return null;
                            }
                            setCache(key, result, timeout, timeUnit);
                            return result;
                        }
                    }
                } else {
                    return null;
                }
            }
            result = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(json, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
