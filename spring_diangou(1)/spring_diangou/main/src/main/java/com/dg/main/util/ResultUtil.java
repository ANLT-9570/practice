package com.dg.main.util;

public class ResultUtil extends Result {
    public ResultUtil(Tuple2<String, String> info, Object data) {
        super(info, data);
    }

    public ResultUtil(String code, String message, Object data) {
        super(code, message, data);
    }
}
