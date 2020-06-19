package com.dg.main.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@TableName("record")
public class Record implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ApiModelProperty(value = "用户id",example = "1")
    @Column(name = "user_id")
    private Long userId;

    @ApiModelProperty(value = "商品id",example = "1")
    @Column(name = "specifications_id")
    private Long specificationsId;

    @ApiModelProperty(value = "店铺id",example = "1")
    @Column(name = "shop_id")
    private Long shopId;

    @ApiModelProperty(value = "浏览时间")
    @Column(name = "browse_time")
    private Timestamp browseTime;

}
