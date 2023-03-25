package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;

/** zset
 */
public class TestZset {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.60.100", 6379);
        jedis.flushDB();
        jedis.zadd("zset", 3,"xiaoming");
        jedis.zadd("zset", 6,"wangwu");
        jedis.zadd("zset", 6,"wangwu");
        jedis.zadd("zset", 2,"zhaoliu");
        jedis.zadd("zset", 2,"zhangsan");
        jedis.zadd("zset", 5,"tianqi");
        jedis.zadd("zset",4,"zhangsanfeng");
        jedis.zadd("zset", 1,"xiaohua");
        jedis.zadd("zset", 10,"qwer");
        jedis.zadd("zset", 11,"oknjkjk");
        jedis.zadd("zset", 17,"xxxxxua");
        jedis.zadd("zset", 21,"woshishi");
        System.out.println("获取zset所有的数据："+jedis.zrange("zset", 0, -1));
        System.out.println("获取成员数量"+jedis.zcard("zset"));
        System.out.println("返回分数范围内得成员数量："+jedis.zcount("zset", 3, 6));
        System.out.println("删除并返回分数最大的值"+jedis.zpopmax("zset"));
        System.out.println("删除并返回分数最大的count个值"+jedis.zpopmax("zset",2));
        System.out.println("删除并返回分数最小的值"+jedis.zpopmin("zset"));
        System.out.println("删除并返回分数最小的count个值"+jedis.zpopmin("zset",2));
        System.out.println("获取指定成员对应的分数："+jedis.zscore("zset", "xiaoming"));
        System.out.println("获取元素对应的索引："+jedis.zrank("zset","xiaoming"));
        System.out.println("删除一个或者多个元素："+jedis.zrem("zset","xiaoming"));
        System.out.println("获取元素对应的索引"+jedis.zrank("zset","xiaoming"));
        System.out.println("获取zset所有的数据："+jedis.zrange("zset", 0, -1));
        System.out.println("根据分数区间根据分数升序排序,并显示元素和分数"+jedis.zrangeByScore("zset",0,22));
        System.out.println("根据分数区间根据分数升序排序,只显示元素"+jedis.zrangeByScoreWithScores("zset",0,22));
        System.out.println("根据分数区间根据分数降序排序,并显示元素和分数"+jedis.zrevrangeByScore("zset", 22, 0));
        System.out.println("根据分数区间根据分数降序排序,只显示元素"+jedis.zrevrangeByScoreWithScores("zset", 22, 0));
    }
}
