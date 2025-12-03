package com.vgbhfive.v_rule.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @projectName: v_rule
 * @author: vgbhfive
 * @date: 2025/11/26 21:50
 */
@Component
public class RedisUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public Integer incrementAndExpire(String type) {
        Long id = redisTemplate.opsForValue().increment(type, 1);
        redisTemplate.expire(type, 1, TimeUnit.DAYS);
        return id.intValue();
    }

    public void set(String key, Object value, Integer time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

}
