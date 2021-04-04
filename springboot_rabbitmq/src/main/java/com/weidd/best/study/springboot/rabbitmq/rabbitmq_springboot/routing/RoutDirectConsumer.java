package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot.routing;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 16:34
 **/

@Component
public class RoutDirectConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列
                    exchange = @Exchange(value = "directRouting",type = "direct"),//定义交换机的名称和类型
                    key = {"info","error","debug"}//指定监听的路由key

            )
    })
    public void routingReceiver(String message){
        System.out.println("消费者1路由模型--> " +message);

    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列
                    exchange = @Exchange(value = "directRouting",type = "direct"),//定义交换机的名称和类型
                    key = {"error"}//指定监听的路由key

            )
    })
    public void routingReceiver2(String message){
        System.out.println("消费者2路由模型--> " +message);

    }
}
