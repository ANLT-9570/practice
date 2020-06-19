//package com.dg.main.rabbitMq;
//
//import com.dg.main.Entity.Items;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListeners;
//import org.springframework.stereotype.Component;
//
////消费者
//
//@Component
//public class AllConsumer {
//
//    @RabbitHandler
//    @RabbitListener(queues = {"TEST1_FANOUT_QUEUE"})//监听队列
//    public void test1Consumer(String msg){
//        System.out.println("test1111111111"+msg);
//    }
//
//    @RabbitHandler
//    @RabbitListener(queues = {"TEST2_FANOUT_QUEUE"})//监听队列
//    public void test2Consumer(String msg){
//        System.out.println("test222222222222"+msg);
//    }
//
//    @RabbitHandler
//    @RabbitListener(queues = {"MYSQL_DATASOURCE_QUEUE"})//监听队列
//    public void MysqlConsumer(Items items){
//        System.out.println("MYSQL_DATASOURCE_QUEUE"+items.toString());
//    }
//
//    @RabbitHandler
//    @RabbitListener(queues = {"ES_QUEUE"})//监听队列
//    public void EsConsumer(Items items){
//        System.out.println("ES_QUEUE"+items.toString());
//    }
//}
