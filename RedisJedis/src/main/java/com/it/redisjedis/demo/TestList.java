package com.it.redisjedis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.args.ListPosition;

import java.util.List;

/**  List
 *
 */
public class TestList {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.120.100", 6379);
        jedis.flushDB();
        //左插入
        Long lpush = jedis.lpush("list1", "1", "2", "4", "5", "3", "9", "8", "8", "8", "9");
        System.out.println("往list里面左添加数据返回长度："+lpush);
        //右插入
        Long rlist = jedis.rpush("rlist", "1", "2", "4", "5", "3", "9", "8", "8", "8", "9");
        System.out.println("往list里面右添加数据返回长度"+rlist);
        //取出list的元素
        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println("取出list里面左添加所有的元素"+list1);
        List<String> list2 = jedis.lrange("rlist", 0, -1);
        System.out.println("取出list里面右添加所有的元素："+list2);
        Long aLong = jedis.llen("list1");
        System.out.println("长度获取："+aLong);
        //替换指定下标的值
        jedis.lset("list1",1,"lset");
        //根据索引获取值
        System.out.println(jedis.lindex("list1",1));
        //从左边删除n个value,
        System.out.println(jedis.lrange("list1",0,-1));
        System.out.println(jedis.lrem("list1",1,"8"));
        System.out.println(jedis.lrange("list1",0,-1));
        //在指定值后插入新的值
        System.out.println(jedis.linsert("list1", ListPosition.AFTER, String.valueOf(4),"linsert"));
        System.out.println(jedis.linsert("list1",ListPosition.BEFORE, String.valueOf(4),"linsert2"));
        System.out.println(jedis.lrange("list1",0,-1));
    }
}
