package com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.fanout;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 20:14
 **/
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        // 创建连接
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs", "fanout");

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();
        //将临时队列和交换机建立连接
        channel.queueBind(queueName, "logs", "");

        //消费消息
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("consumer2获取到了消息: " + new String(body));
            }
        });

    }
}
