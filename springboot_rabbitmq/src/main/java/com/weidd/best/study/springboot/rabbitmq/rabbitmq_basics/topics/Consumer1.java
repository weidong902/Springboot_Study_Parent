package com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.topics;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 11:00
 **/
public class Consumer1 {
    public static void main(String[] args) throws IOException {

        //获取连接
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通道绑定交换机
        String exchangeName = "logs_topic";
        channel.exchangeDeclare(exchangeName, "topic");
        //获取临时队列
        String queue = channel.queueDeclare().getQueue();
        //队列绑定交换机
        String routingKey = "user.*";
        channel.queueBind(queue, exchangeName, routingKey);
        //消费消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1 接收到了消息--> " + new String(body));
            }
        });

    }
}
