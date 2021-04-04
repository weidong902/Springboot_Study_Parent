package com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.workqueue;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 17:56
 **/
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只能消费一个消息
        channel.queueDeclare("workqueue", true, false, false, null);
        channel.basicConsume("workqueue", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("Consumer1 获取到工作消息--> "+new String(body));
                //手动确认 参数1:手动确认消息标识 ,参数2:false,每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }
}
