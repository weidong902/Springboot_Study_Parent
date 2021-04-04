package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 16:22
 **/
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(value = "logsExchange", type = "fanout")
    )
    })
    public void receiverMessage(String message) {
        System.out.println("message1 ->" + message);
    }

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue,//创建临时队列
            exchange = @Exchange(value = "logsExchange", type = "fanout")
    )
    })
    public void receiverMessage2(String message) {
        System.out.println("message2 ->" + message);
    }
}
