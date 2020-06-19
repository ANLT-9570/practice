package com.dg.main.serviceImpl;

import com.dg.main.Entity.users.UserRealName;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.service.CertificationServer;
import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificationServiceImpl implements CertificationServer {

    @Autowired
    UserRealNameRepository userRealNameRepository;

    @Override
    public Result pass(Long id, Integer status, Integer isPassed) {
        Optional<UserRealName> optional = userRealNameRepository.findById(id);
        if (optional.isPresent()){
            UserRealName userRealName = optional.get();
            userRealName.setStatus(status);
            userRealName.setIsPassed(isPassed);
            UserRealName save = userRealNameRepository.save(userRealName);
            if(save != null){
                return Result.successResult();
            }
        }
        return Result.failureResult("43990","修改失败");
    }

    @Override
    public slzcResult list(Integer offset, Integer limit, String search) {
        if(StringUtils.isNotBlank(search)){

        }
        List<UserRealName> list = userRealNameRepository.findAll(offset,limit);
        long count = userRealNameRepository.count();
        slzcResult result = new slzcResult();
        result.setRows(list);
        result.setTotal(count);
        return result;
    }
}
