package com.jinunn.send;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author : Jin
 * @date : 2022/6/15 16:58
 */
@Slf4j
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String KEY = "jinunn";

    @Test
    public void set() {
        assertNotNull(redisTemplate);
        redisTemplate.opsForValue().set(KEY, "hello redis");
        log.info("成功");
    }

    @Test
    public void getValue() {
        assertNotNull(redisTemplate);
        String value = redisTemplate.opsForValue().get(KEY);
        log.info("value ==> {}", value);
    }
}
