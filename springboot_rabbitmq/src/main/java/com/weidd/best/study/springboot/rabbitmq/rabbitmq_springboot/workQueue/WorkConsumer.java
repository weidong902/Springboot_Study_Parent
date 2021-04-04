package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot.workQueue;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 16:05
 **/
@Component
public class WorkConsumer {

    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("workqueue"))
    public void workConsumer(String message) {
        System.out.println("消费者1--> " + message);
    }

    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("workqueue"))
    public void workConsumer2(String message) {
        System.out.println("消费者2--> " + message);
    }


}
