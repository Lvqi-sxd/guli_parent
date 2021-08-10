package test;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Administrator
 * 2021-8-6
 */
public class redisTest {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println("连接成功");
        // 获取存储的数据并输出
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        for (String key : keys) {
            System.out.println(key+":"+jedis.get(key));
        }
        System.out.println();
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }

}
