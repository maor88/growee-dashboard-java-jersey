package com.growee.database.redis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Maor
 *
 */
public class RedisPool {

    private static JedisPool pool;
    private static final int MAX_REDIS_OPEN_CONNECTIONS = 100;

    public static JedisPool getPool() {
        if (pool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MAX_REDIS_OPEN_CONNECTIONS);
            pool = new JedisPool(config,"host-url",6379);
            System.out.println("Connected to Redis.");
        }

        return pool;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        pool.destroy();
    }
}
