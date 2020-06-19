package com.dg.main.Entity.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_company_employee_profit")
@NoArgsConstructor
@Data
@ToString
public class UserComanyEmployeeProfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shop_id")
    private Long shopId;
    @Column(name = "owner_id")
    private Long ownerId;
    @Column(name = "buyer_id")
    private Long buyerId;
    @Column(name = "years")
    private Integer years;
    @Column(name = "months")
    private Integer months;
    @Column(name = "days")
    private Integer days;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "money")
    private Long money;
}
