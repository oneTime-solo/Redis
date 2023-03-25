package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;
/*
 set
*/

public class TestSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.120.100",6379);
        //sadd  添加
        jedis.sadd("set1","1","2","3","4","5","4","3","4","3","7","8");
        //smemebers 获取集合里所有的元素
        System.out.println("获取集合里所有的元素："+jedis.smembers("set1"));
        //返回集合的元素个数
        System.out.println("集合里面元素个数："+jedis.scard("set1"));
        //判断某个值是否存在于某个集合
        System.out.println("集合里是否包含某个元素："+jedis.sismember("set1","7"));
        System.out.println("集合里是否包含某个元素："+jedis.sismember("set1","22"));
        //删除集合中某个元素
        System.out.println(jedis.srem("set1","7"));
        System.out.println("集合里是否包含某个元素："+jedis.sismember("set1","7"));
        //删除并返回一个或者多个随即元素，会删除
        System.out.println(jedis.smembers("set1"));
        System.out.println("删除并返回一个或者多个随即元素："+jedis.spop("set1",4));
        System.out.println(jedis.smembers("set1"));
        //从集合中随机取出几个值，不会删除
        System.out.println("从集合中随机取出几个值，不会删除:"+jedis.srandmember("set1"));
        //在两个集合中移动值
        jedis.sadd("set2","2");
        jedis.sadd("set1","2");
        System.out.println(jedis.smembers("set1"));
        System.out.println("在两个集合中移动值："+jedis.smove("set1","set2","8"));
        System.out.println(jedis.smembers("set1"));
        System.out.println(jedis.smembers("set2"));

        //返回两个集合的交集
        System.out.println("返回两个集合的交集："+jedis.sinter("set1","set2"));
        //返回两个集合的差集
        System.out.println("返回两个集合的差集:"+jedis.sdiff("set1","set2"));
        //返回两个集合的并集
        System.out.println("返回两个集合的并集:"+jedis.sunion("set1","set2"));
        //把两个集合的差集放到另一个集合中， 如果被放置差集的集合存在会覆盖，否则创建。
        System.out.println("sdiffstore:"+jedis.sdiffstore("set","set1","set2"));
        System.out.println(jedis.smembers("set"));
    }
}
