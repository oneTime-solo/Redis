package com.it.redisjedis.demo;

import org.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/*
 * 事务
 * */
public class TestTX {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hello", "word");
        jsonObject.put("name", "zhangsan1");
        String reult = jsonObject.toString();

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        Transaction multi = jedis.multi();//开启事务
        try {
            multi.set("user1", reult);
            multi.set("user2", reult);
            int i = 10/0;//代码抛出异常事务执行失败
            multi.exec();//成功的话提交事务
        } catch (Exception e) {
            multi.discard();//失败的话放弃事务
            e.printStackTrace();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));

            jedis.close();//关闭连接
        }
    }
}
