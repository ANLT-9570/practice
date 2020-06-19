package com.dg.main.serviceImpl.logs;


import com.dg.main.Entity.logs.ZhifubaoCallbackFailureLog;
import com.dg.main.repository.log.ZhifubaoCallbackFailureLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZhifubaoCallbackFailureLogService {
    @Autowired
    ZhifubaoCallbackFailureLogRepository repository;


    public ZhifubaoCallbackFailureLog findBy(Long id) {

        return repository.getOne(id);
    }






    public void save(ZhifubaoCallbackFailureLog t) {

         repository.save(t);
    }



    public List<ZhifubaoCallbackFailureLog> selectAll(ZhifubaoCallbackFailureLog t) {
        // TODO Auto-generated method stub
        return null;
    }



}
