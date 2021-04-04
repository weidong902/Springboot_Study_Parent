package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot.topics;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 16:43
 **/
@Component
public class TopicConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topicsRouting",type = "topic"),
                    key = {"user.*","user.#"}
            )
    })
    public void getMessage(String message) {
        System.out.println("基于动态路由的消费者1--> " + message);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "topicsRouting",type = "topic"),
                    key = {"user.*"}
            )
    })
    public void getMessage2(String message) {
        System.out.println("基于动态路由的消费者2--> " + message);
    }
}
