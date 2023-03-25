package com.it.redisjedis.demo;

import com.sun.xml.internal.ws.util.StringUtils;
import jdk.nashorn.internal.parser.Scanner;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import sun.swing.StringUIClientPropertyKey;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 要求：
 * 1、输入手机号，点击发送随机生成六位数验证码，两分钟有效
 * 2、输入验证码，点击验证，返回成功或者失败
 * 3、每个手机号每天只能输入三次
 */
public class TestEg {
    @Value("spring.redis.host")
    private String host;
    @Value("spring.redis.port")
    private String port;
    //手机号
    static String phone = "19939485700";
    //拼接key
    //手机发送次数key
    static String countKey = "verifCode" + phone + ":count";
    //验证码key
    static String phoneCode = "verifCode" + phone + ":code";
    //连接信息
    static Jedis jedis = new Jedis("192.168.120.100", 6379);

    public static void main(String[] args) {
        String code = getCode();
        if (verifCode(code)) {
            getRedisCode(code);
        }
    }

    //1、生成六位数字的验证码
    public static String getCode() {
        SecureRandom random = new SecureRandom();
        String rand = "";
        for (int i = 0; i < 6; i++) {
            //在0~10之间生成随机整数，不包含10
            int nextInt = random.nextInt(10);
            rand += nextInt;
        }
        return rand;
    }

    //2、设置每个手机每天只能发送三次，验证码放到redis中，设置过期时间
    public static boolean verifCode(String code) {
        boolean flag = true;
        //每个手机每天只能发送三次
        String count = jedis.get(countKey);
        if (count == null) {
            //指定的 key 设置值及其过期时间。如果 key 已经存在， SETEX 命令将会替换旧的值
            jedis.setex(countKey, 24 * 60 * 60, "1");
        } else if (Integer.parseInt(count) < 3) {
            jedis.incr(countKey);
            //发送验证码，并把验证码放到redis
            jedis.setex(phoneCode, 120, code);
        } else {
            System.out.println("今天的发送次数已经用完了");
            jedis.close();
            flag = false;
            return flag;
        }
        jedis.close();
        return flag;
    }

    //验证码校验
    public static void getRedisCode(String code) {
        //验证码key
        String redisCode = jedis.get(phoneCode);
        if (redisCode == null) {
            System.out.println("请获取验证码！");
        } else if (!code.equals(redisCode)) {
            System.out.println("验证码错误！");
        } else {
            System.out.println("成功！");
        }
    }
}
