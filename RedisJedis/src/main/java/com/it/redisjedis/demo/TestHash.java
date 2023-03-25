package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

/**
 *  hash
 */
public class TestHash {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.120.100",6379);
        jedis.flushDB();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("name","zhangsan");
        hashMap.put("sex","女");
        hashMap.put("addr","上海");
        String s = jedis.hmset("users", hashMap);
        System.out.println(s);
        System.out.println(jedis.hgetAll("users"));
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("age","20");
        map.put("phone","110");
        System.out.println("hash类型添加数据："+jedis.hmset("users", map));
        System.out.println("取出指定key和filed对应的值："+jedis.hget("users", "name"));
        System.out.println("取出key中所有的数据："+jedis.hgetAll("users"));
        System.out.println("判断某个filed是否存在："+jedis.hexists("users", "name"));
        System.out.println("列出指定key的所有的filed:"+jedis.hkeys("users"));
        System.out.println("列出hash集合的所有值："+jedis.hvals("users"));
        System.out.println("将指定key中的field设置为value,如果不存在添加，存在不添加:"+jedis.hsetnx("users","name","lisi"));
        System.out.println("将指定key中的field设置为value,如果不存在添加，存在不添加:"+jedis.hsetnx("users","del_flage","0"));
        System.out.println("取出key中所有的数据："+jedis.hgetAll("users"));
        System.out.println("获取hash所有字段的长度："+jedis.hlen("users"));
        System.out.println("将指定hash域的值增加指定长度："+jedis.hincrBy("users","age",5));
        System.out.println("将指定hash域的值增加指定长度："+jedis.hincrBy("users","age",-2));
        System.out.println("将指定hash域的值增加指定长度："+jedis.hincrByFloat("users","age",0.6));
        System.out.println("删除指定的field："+jedis.hdel("users","phone"));
        System.out.println("获取hash指定field的长度："+jedis.hstrlen("users","name"));
      //  System.out.println("迭代hash里面的元素："+jedis.hscan("users", "0").getResult());
        System.out.println("取出key中所有的数据："+jedis.hgetAll("users"));
    }
}
