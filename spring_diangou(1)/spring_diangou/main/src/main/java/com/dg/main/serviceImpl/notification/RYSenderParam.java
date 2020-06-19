package com.dg.main.serviceImpl.notification;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class RYSenderParam {
    private String[] userIds=new String[]{};
    private Integer type=0;
    private String[] platforms=new String[]{};
    private String title;
    private String content;








}
