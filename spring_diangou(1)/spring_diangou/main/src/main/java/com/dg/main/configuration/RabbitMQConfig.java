package com.dg.main.configuration;

import com.dg.main.enums.QueueNameEnum;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //队列名称
    private static final String TEST1_FANOUT_QUEUE = "TEST1_FANOUT_QUEUE";

    private static final String TEST2_FANOUT_QUEUE = "TEST2_FANOUT_QUEUE";

    //mysql队列
//    private static final String MYSQL_DATASOURCE_QUEUE = "MYSQL_DATASOURCE_QUEUE";
    //es队列
//    private static final String ES_QUEUE = "ES_QUEUE";

    //交换机名称
    private static final String EXCHANGE_NAME = "TEST2_FANOUT_QUEUE";

    //定义队列1
    @Bean
    public Queue fanoutTest1Queue(){
        return new Queue(TEST1_FANOUT_QUEUE);
    }

    //定义队列2
    @Bean
    public Queue fanoutTest2Queue(){
        return new Queue(TEST2_FANOUT_QUEUE);
    }

    //mysql队列
    @Bean
    public Queue fanoutMysqlDatasourceQUEUE(){
        return new Queue(QueueNameEnum.MYSQL_DATASOURCE_QUEUE.getName());
    }

    //es队列
    @Bean
    public Queue fanoutESQueue(){
        return new Queue(QueueNameEnum.ES_QUEUE.getName());
    }

    //定义交换机
    @Bean
    public FanoutExchange FanoutExchange(){
        return new FanoutExchange(EXCHANGE_NAME);
    }

    //队列1和交换机绑定
    public Binding bindingExchangeAndTest1(Queue fanoutTest1Queue, FanoutExchange FanoutExchange){
        return BindingBuilder.bind(fanoutTest1Queue).to(FanoutExchange);
    }

    //队列2和交换机绑定
    public Binding bindingExchangeAndTest2(Queue fanoutTest2Queue, FanoutExchange FanoutExchange){
        return BindingBuilder.bind(fanoutTest2Queue).to(FanoutExchange);
    }

    //mysql队列和交换机绑定
    public Binding bindingExchangeAndmysqls(Queue fanoutMysqlDatasourceQUEUE, FanoutExchange FanoutExchange){
        return BindingBuilder.bind(fanoutMysqlDatasourceQUEUE).to(FanoutExchange);
    }

    //es队列和交换机绑定
    public Binding bindingExchangeAndEs(Queue fanoutESQueue, FanoutExchange FanoutExchange){
        return BindingBuilder.bind(fanoutESQueue).to(FanoutExchange);
    }
}
