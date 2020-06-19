package com.dg.main.serviceImpl.orders;

import com.google.gson.Gson;
import com.dg.main.exception.BaseException;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProccess implements Proccess{
    protected List<BaseException> exceptions=new ArrayList<>();
    protected Gson gson=new Gson();
    public List<BaseException> getException() {
        return exceptions;
    }

    public BaseProccess() {
    }
    final public void exec()throws Exception{
        if(validate()){
           action();
           notifyChange();
        }
    }
}
