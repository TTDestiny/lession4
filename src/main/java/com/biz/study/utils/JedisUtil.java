package com.biz.study.utils;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {
    private static String ADDR = "47.94.235.143";
    private static int PORT = 6379;
    private static String AUTH = "redispwd";
    private static int MAXTOTAL = 200;
    private static int MAXIDLE = 100;
    private static int TIMEOUT = 100000;
    private static int WAITMILLIONS = 10000;

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAXTOTAL);
            config.setMaxWaitMillis(WAITMILLIONS);
            config.setMaxIdle(MAXIDLE);
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void releaseReourse(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
