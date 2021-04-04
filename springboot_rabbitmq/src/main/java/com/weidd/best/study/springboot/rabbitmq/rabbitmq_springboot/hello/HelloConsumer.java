package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 15:42
 **/
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "helloworld",durable = "true",autoDelete = "false"))
public class HelloConsumer {

    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("message--> "+message);
    }

}
