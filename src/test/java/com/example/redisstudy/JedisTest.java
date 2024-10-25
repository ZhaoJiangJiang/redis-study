package com.example.redisstudy;

import com.alibaba.fastjson2.JSON;
import com.example.redisstudy.config.MyRedisConfig;
import com.example.redisstudy.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
@Slf4j
public class JedisTest {

    @Autowired
    private MyRedisConfig redisConfig;

    @Test
    public void testRedisStringSet() {
        Person person = Person.builder()
                .name("Alice")
                .gender("Female")
                .hobby("Dancing")
                .age(26)
                .build();
        // 使用 FastJson 对用户信息进行序列化
        String personString = JSON.toJSONString(person);
        Jedis jedis = new Jedis(redisConfig.getHost(), redisConfig.getPort());
        // 将JSON字符串存入Redis
        jedis.set("person", personString);
        // 关闭jedis连接
        jedis.close();
    }

    @Test
    public void testRedisStringGet() {
        try (Jedis jedis = new Jedis(redisConfig.getHost(), redisConfig.getPort())) {
            String personString = jedis.get("person");
            // 反序列化
            Person person = JSON.parseObject(personString, Person.class);
            log.info("person = {}", person.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
