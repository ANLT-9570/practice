package com.dg.main.enums;

import lombok.Data;

public enum QueueNameEnum {

    MYSQL_DATASOURCE_QUEUE("MYSQL_DATASOURCE_QUEUE"),
    ES_QUEUE("ES_QUEUE")
    ;

    private String name;

    public String getName() {
        return name;
    }

    private QueueNameEnum(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserStreamEnum{" +
                ", name='" + name + '\'' +
                '}';
    }
}
