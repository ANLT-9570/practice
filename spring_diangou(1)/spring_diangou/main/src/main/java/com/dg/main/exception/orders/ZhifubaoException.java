package com.dg.main.exception.orders;

import com.dg.main.exception.BaseException;
import com.dg.main.util.Tuple2;

public class ZhifubaoException extends BaseException {
    private final Tuple2<String,String> info;

    public ZhifubaoException(Tuple2<String, String> info) {
        this.info = info;
        code=info._1;
        message=info._2;
    }
}
