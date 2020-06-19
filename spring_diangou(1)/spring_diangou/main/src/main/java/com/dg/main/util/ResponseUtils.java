package com.dg.main.util;

import com.google.gson.Gson;
import com.dg.main.exception.ExceptionCode;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class ResponseUtils {
    public static void send(HttpServletResponse response,Result result)throws Exception{
        Gson gson=new Gson();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();

        writer.write(gson.toJson(result));
        writer.flush();
        writer.close();
    }
}
