package com.dg.main.Entity.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "specification_join_sku")
public class SpecificationJoinSKU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "specificatoin_id")
    private Long specificationId;
    @Column(name = "sku_join_paramter_id")
    private Long skuJoinParamterId;
    @Column(name = "parameter_id")
    private Long parameterId;
    @Column(name = "stock")
    private Long stock;
    @Column(name = "money")
    private Long money;
    @Column(name = "img")
    private String img;
}
