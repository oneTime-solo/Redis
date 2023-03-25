package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;

public class TestKey {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.120.100", 6379);
        System.out.println("删除当前选择数据库中的所有key"+jedis.flushDB());
        System.out.println("判断某个键是否存在"+jedis.exists("key"));
        //新增
        System.out.println("新增键值对："+jedis.set("username","zhangsan"));
        System.out.println("新增键值对："+jedis.set("password","123456"));
        //设置过期时间
        System.out.println(jedis.expire("password",4));
        //新增并设置过期时间
        System.out.println("新增键值对并设置过期时间："+jedis.setex("one",5,"hello Redis"));
        //剩余过期时间
        System.out.println("当前键剩余过期时间："+jedis.ttl("one"));
        System.out.println("当前键剩余过期时间："+jedis.ttl("password"));
        //根据key获取值
        System.out.println("取出username："+jedis.get("username"));
        //key*
        System.out.println("系统所有的键：");
        System.out.println(jedis.keys("*"));
        System.out.println("模糊查询key"+jedis.keys("*ne"));
        //删除
        System.out.println("删除password:"+jedis.del("password"));
        //判断Key是否存在
        System.out.println("判断password是否存在"+jedis.exists("password"));
        //判断username存储类型
        System.out.println("判断username存储类型"+jedis.type("username"));
        //截取指定key的值
        System.out.println("截取指定key的值："+jedis.getrange("username",1,3));
        //命令从当前数据库中随机返回一个 key
        System.out.println("命令从当前数据库中随机返回一个 key："+jedis.randomKey());
        //获取值的长度
        System.out.println("获取值的长度："+jedis.strlen("username"));
        System.out.println("取出username："+jedis.get("username"));
        //重命名key
        System.out.println("重命名key："+jedis.rename("username","name"));
        System.out.println("取出修改后的name"+jedis.get("name"));
        //setnx   当key不存在时设置值，存在时无效
        jedis.setnx("key1", "23");
        jedis.set("key2","55");
        jedis.setnx("key2","44");
        System.out.println("setnx:"+jedis.get("key1"));
        System.out.println("setnx:"+jedis.get("key2"));
        //切换数据库
        System.out.println("切换数据库：："+jedis.select(1));
        System.out.println("当前库是："+jedis.keys("*"));
//        System.out.println("删除所有数据库中的所有key:"+jedis.flushAll());
        System.out.println("返回当前数据库中key的数量:"+jedis.dbSize());
    }
}
