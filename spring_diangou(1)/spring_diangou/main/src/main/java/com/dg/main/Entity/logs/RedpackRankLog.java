package com.dg.main.Entity.logs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "red_pack_rank_log")
@Data
@ToString
@NoArgsConstructor
public class RedpackRankLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
