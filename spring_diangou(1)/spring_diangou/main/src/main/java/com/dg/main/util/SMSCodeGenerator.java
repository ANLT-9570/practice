package com.dg.main.util;

import java.util.concurrent.ThreadLocalRandom;

public class SMSCodeGenerator {
    public static String code4(){
        String str="0123456789";
        ThreadLocalRandom random=ThreadLocalRandom.current();
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
//public int nextInt(int n) 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
            char ch=str.charAt(random.nextInt(str.length()));
            sb.append(ch);
        }
        System.out.println("-------sms-----4位--->"+sb.toString());
    return sb.toString();

    }
    public static String code6(){
        String str="0123456789";
        ThreadLocalRandom random=ThreadLocalRandom.current();
        StringBuilder sb=new StringBuilder(6);
        for(int i=0;i<6;i++)
        {
//public int nextInt(int n) 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
            char ch=str.charAt(random.nextInt(str.length()));
            sb.append(ch);
        }
        System.out.println("-------sms-----6位--->"+sb.toString());
        return sb.toString();
    }
}
