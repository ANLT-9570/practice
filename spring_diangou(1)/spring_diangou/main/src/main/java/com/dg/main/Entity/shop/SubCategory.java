package com.dg.main.Entity.shop;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sub_category")
@Data
public class SubCategory {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "parent_id")
    private Long parentId;
    @Column(name = "name")
    private String name;
    @Column(name = "create_time")
    private Date createTime=new Date();

    public SubCategory() {
    }
}
