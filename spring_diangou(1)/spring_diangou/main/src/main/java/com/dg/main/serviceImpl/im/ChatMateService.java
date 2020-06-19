package com.dg.main.serviceImpl.im;

import com.dg.main.repository.im.ChatMateRepository;
import com.dg.main.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMateService {
    @Autowired
    ChatMateRepository repository;
    public Result mateList(Long id){
        return Result.successResult(repository.findByUserId(id));
    }
}
