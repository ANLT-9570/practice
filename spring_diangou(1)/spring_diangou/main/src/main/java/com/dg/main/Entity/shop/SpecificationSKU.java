package com.dg.main.Entity.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.NavigableMap;

@Entity
@Table(name = "specification_sku")
@Data
@ToString
@NoArgsConstructor
public class SpecificationSKU  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createTime=new Date();
    private Integer type;
}
