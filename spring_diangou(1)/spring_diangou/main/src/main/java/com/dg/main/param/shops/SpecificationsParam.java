package com.dg.main.param.shops;

import cn.jpush.api.TestOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.compress.utils.Lists;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
@Data
@ToString
@NoArgsConstructor
public class SpecificationsParam {
    private List<SKUParam> items= Lists.newArrayList();

    private Long 	id;
    @ApiModelProperty(value = "二级分类",example = "1")
    private String subCategory;
    @ApiModelProperty(value = "商家id",example = "1")
    private Long userId; //商家id

    @ApiModelProperty(value = "描述")
    private String  describe;//描述



    @ApiModelProperty(value = "单价",example = "1")
    private Long price; //单价

    @ApiModelProperty(value = "折扣价",example = "1")
    private Long discountPrice; //折扣价

    @ApiModelProperty(value = "品牌")
    private String brand; //品牌

    @ApiModelProperty(value = "数量",example = "1")
    private Long number ;// 数量

    @ApiModelProperty(value = "简介")
    private String mydescribe;



    @ApiModelProperty(value = "售后服务")
    private String salesService;//售后服务



    @ApiModelProperty(value = "1上架，2下架，3删除",example = "1")
    private Integer status;//1上架，2下架，3删除
    private Long postMoney=0l;


    @ApiModelProperty(value = "商品名称")
    private String name;//商品名称



    @ApiModelProperty(value = "分类")
    private Integer category;//分类

    @ApiModelProperty(value = "国家")
    @Column(name = "country")
    private String country;//国家



    @ApiModelProperty(value = "缩略图")
    private String thumbnailImage;

    @ApiModelProperty(value = "原图")
    private String originalImage;
    private Long shopId;
    private String img;


}
