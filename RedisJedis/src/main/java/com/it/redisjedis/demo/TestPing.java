package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;
/*
* 测试连接是否成功
*
*
* */
public class TestPing {
    public static void main(String[] args) {
        //1、new  Jedis对象
        Jedis jedis = new Jedis("192.168.120.100", 6379);
        //测试是否连接成功    成功---PONG
        System.out.println(jedis.ping());
    }
}
