package com.dg.main.exception.orders;

import com.dg.main.exception.BaseException;
import com.dg.main.util.Tuple2;

public class WeixinWithdrawFailureException  extends BaseException {
    private final Tuple2<String,String> info;

    public WeixinWithdrawFailureException(Tuple2<String, String> info) {
        this.info = info;
        code=info._1;
        message=info._2;

    }

    public Tuple2<String, String> getInfo() {
        return info;
    }
}
