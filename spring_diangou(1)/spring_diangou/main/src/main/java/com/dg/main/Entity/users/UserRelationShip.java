package com.dg.main.Entity.users;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_relation_ship")
public class UserRelationShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "ship_user_id")
    private Long shipUserId;
    @Column(name = "create_time")
    private Date createTime=new Date();
}
