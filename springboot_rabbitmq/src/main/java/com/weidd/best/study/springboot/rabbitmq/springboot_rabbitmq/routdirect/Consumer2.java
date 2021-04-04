package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.routdirect;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 09:33
 **/
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct", "direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "logs_direct", "error");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("Consumer2 获取消息--> " + new String(body));
            }
        });


    }
}
