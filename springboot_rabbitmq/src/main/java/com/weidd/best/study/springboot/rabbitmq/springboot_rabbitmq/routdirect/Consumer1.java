package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.routdirect;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 09:33
 **/
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定队列
        channel.exchangeDeclare("logs_direct", "direct");
        // 获取临时queue
        String queue = channel.queueDeclare().getQueue();
        //将队列和交换机绑定,同时声明接受那些routing key
        channel.queueBind(queue, "logs_direct", "info");
        channel.queueBind(queue, "logs_direct", "error");
        channel.queueBind(queue, "logs_direct", "warning");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("Consumer1 获取消息--> " + new String(body));
            }
        });


    }
}
