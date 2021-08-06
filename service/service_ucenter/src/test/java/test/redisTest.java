package test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Administrator
 * 2021-8-6
 */
public class redisTest {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void setRedis(){
        redisTemplate.opsForValue().set("18845099073","123456");
        System.out.println("设置成功");

    }
}
