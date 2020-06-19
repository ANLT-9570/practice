package com.dg.main.Entity.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sun.util.calendar.LocalGregorianCalendar;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "user_bank_card")
public class UserBankCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "type")
    private Integer type;
    @Column(name = "code")
    private String code;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "img")
    private String img;
}
