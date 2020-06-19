package com.dg.main.serviceImpl.shop;

import com.dg.main.Entity.shop.Size;
import com.dg.main.dto.shop.SKUDto;
import com.dg.main.dto.shop.SKUParamDto;
import com.dg.main.param.shops.SKUParam;
import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.util.Result;
import org.apache.commons.compress.utils.Lists;
import org.ini4j.spi.RegEscapeTool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService {
    @Autowired
    SizeRepository sizeRepository;
    public Result getNormalList(Long specificationId){
        return Result.successResult(sizeRepository.findBySpecificationsId(specificationId));
    }
    public Result getList(Long specificatioId){
        List<SKUDto> skus= Lists.newArrayList();
        List<Size> sizes=sizeRepository.findBySpecificationsId(specificatioId);
        List<String> labels= com.google.common.collect.Lists.newArrayList("颜色");
        for (String label : labels){
            SKUDto skuDto=new SKUDto();
            skuDto.setSpecificationId(specificatioId);
            skuDto.setLabelName(label);
            for(Size inside:sizes){
                skuDto.getParams().add(inside.getColour());
            }
            skus.add(skuDto);
        }
        List<String> labels2= com.google.common.collect.Lists.newArrayList("大小");
        for (String label : labels2){
            SKUDto skuDto=new SKUDto();
            skuDto.setLabelName(label);
            skuDto.setSpecificationId(specificatioId);
            for(Size inside:sizes){
                skuDto.getParams().add(inside.getDimensions());
            }
            skus.add(skuDto);
        }
        return Result.successResult(skus);
    }
    public Result getListByParam(Long specificatioId,String color,String dimention){
        Size size=sizeRepository.findBySpecificationsIdAndColourAndDimensions(specificatioId,color,dimention);
        SKUParamDto paramDto=new SKUParamDto();
        BeanUtils.copyProperties(size,paramDto);
        paramDto.setPrice(size.getPrice());
        paramDto.setStock(size.getStock());
        return Result.successResult(paramDto);
    }

}
