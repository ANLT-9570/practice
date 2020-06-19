package com.dg.main.util;

public class SuccessResult extends Result {


    public SuccessResult(Tuple2<String, String> info, Object data) {
        super(info, data);
    }

    public SuccessResult(String code, String message, Object data) {
        super(code, message, data);
    }
}