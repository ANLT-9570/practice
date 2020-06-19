//package com.dg.main.rabbitMq;
//
//import com.dg.main.Entity.Items;
//import com.dg.main.enums.QueueNameEnum;
//import com.dg.main.enums.UserStreamEnum;
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
////生产者
//
//@Component
//public class FanoutProducer {
//
//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    public void send(String queueName){
//
//        System.out.println(queueName);
//
//        String message = "测试试试试试试试试试试";
//
//        amqpTemplate.convertAndSend(queueName,message);
//    }
//
//    public void asyn(Items items){
//
//        System.out.println(items);
//        String message = "测试试试试试试试试试试";
////        amqpTemplate.
//        amqpTemplate.convertAndSend(QueueNameEnum.MYSQL_DATASOURCE_QUEUE.getName(),items);
//        amqpTemplate.convertAndSend(QueueNameEnum.ES_QUEUE.getName(),items);
//    }
//
//}
