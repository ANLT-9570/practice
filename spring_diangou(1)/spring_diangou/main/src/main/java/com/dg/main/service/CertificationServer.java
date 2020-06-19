package com.dg.main.service;

import com.dg.main.util.Result;
import com.dg.main.util.slzcResult;

public interface CertificationServer {
    Result pass(Long id, Integer status, Integer isPassed);

    slzcResult list(Integer offset, Integer limit, String search);

}
