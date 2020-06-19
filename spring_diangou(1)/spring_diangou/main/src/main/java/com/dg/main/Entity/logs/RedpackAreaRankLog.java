package com.dg.main.Entity.logs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "red_pack_area_rank_log")
@Data
@ToString
@NoArgsConstructor
public class RedpackAreaRankLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_time")
    private String createTime=null;
    @Column(name = "red_pack_id")
    private Long redPackId;
    @Column(name = "money")
    private Long money;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "ranks")
    private Long ranks;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "province")
    private String province;
    @Column(name = "city")
    private String city;


}

//set 过期时间为48小时，按city  createTime  shopId
