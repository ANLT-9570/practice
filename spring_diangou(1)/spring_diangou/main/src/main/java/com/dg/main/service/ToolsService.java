package com.dg.main.service;

import com.dg.main.util.Result;

public interface ToolsService {
    Result shopUproar(Integer offset,Integer limit);

    Result commodityUproar(Integer offset, Integer limit);

    Result shopWithSpecification(Integer offset, Integer limit, Long shopId);

    Result commodityForTop(Long specificationId);
}
