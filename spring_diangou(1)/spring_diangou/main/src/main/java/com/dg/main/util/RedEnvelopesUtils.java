package com.dg.main.util;

import java.util.ArrayList;
import java.util.List;

public class RedEnvelopesUtils {
    private static final int MINMONEY =1;
    private static final double TIMES =2;
    public List<Long> splitRedPackets(Long money, int count,Long maxMoney){
        //红包 合法性校验
        if(!isRight(money,count,maxMoney)){
            return null;
        }
        //红包列表
        List<Long> list =new ArrayList<Long>();
        //每个红包最大的金额为平均金额的Times 倍
        long max =(int)(money*TIMES/count);

        max = max>maxMoney ? maxMoney : max;
        //分配红包
        for (int i = 0; i < count; i++) {
            long one = randomRedPacket(money,MINMONEY,max,count-i,maxMoney);
            list.add(one);
            money -=one;
        }
        return list;
    }
    private long randomRedPacket(long money, long minS, long maxS, long count,long maxMoney) {
        //若是只有一个，直接返回红包
        if(count==1){
            return money;
        }
        //若是最小金额红包 == 最大金额红包， 直接返回最小金额红包
        if(minS ==maxS){
            return minS;
        }
        //校验 最大值 max 要是比money 金额高的话？ 去 money 金额
        long max = maxS>money ? money : maxS;
        //随机一个红包 = 随机一个数* (金额-最小)+最小
        long one =((int)Math.rint(Math.random()*(max-minS)+minS));
        //剩下的金额
        long moneyOther =money-one;
        //校验这种随机方案是否可行，不合法的话，就要重新分配方案
        if(isRight(moneyOther, count-1,maxMoney)){
            return one;
        }else{
            //重新分配
            double avg =moneyOther /(count-1);
            //本次红包过大，导致下次的红包过小；如果红包过大，下次就随机一个小值到本次红包金额的一个红包
            if(avg<MINMONEY){
                //递归调用，修改红包最大金额
                return randomRedPacket(money, minS, one, count,maxMoney);

            }else if(avg>maxMoney){
                //递归调用，修改红包最小金额
                return randomRedPacket(money, one, maxS, count,maxMoney);
            }
        }
        return one;
    }
    /**
     * 红包 合法性校验
     * @param money
     * @param count
     * @return
     */
    private boolean isRight(long money, long count,long maxMoney) {
        double avg =money/count;
        //小于最小金额
        if(avg<MINMONEY){
            return false;
            //大于最大金额
        }else if(avg>maxMoney){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        //随机一个188.88  5个红包
        RedEnvelopesUtils dd = new RedEnvelopesUtils();
        Long maxMoney=1000000000l;
        //单位是分
        System.out.println(dd.splitRedPackets(10000l, 10000/2,maxMoney));
    }


}
