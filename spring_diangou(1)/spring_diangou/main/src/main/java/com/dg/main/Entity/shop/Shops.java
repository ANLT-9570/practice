package com.dg.main.Entity.shop;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shops")
@Data
@ToString
@NoArgsConstructor
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "brand_img")
    private String brandImg;
    @Column(name = "display_img")
    private String displayImg;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String descriptions;
    @Column(name = "browse_number")
    private Long browseNumber;
    @Column(name = "create_time")
    private Date createTime=new Date();
    @Column(name = "income")
    private Long income;
    @Column(name = "city")
    private String city;
//    @Column(name = "area")
//    private String area;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "province")
    private String province;
    @Column(name = "lat")
    private String lat;
    @Column(name = "log")
    private String lon;
    @Column(name = "addr")
   private String addr;
    @Column(name = "is_delete")
    private Integer isDelete=2;
    @Column(name = "is_default")
    private Integer isDefault=2;
    @Column(name = "register_id")
    private String registerID;



    @Column(name = "chat_token")
    private String chatToken;


}
