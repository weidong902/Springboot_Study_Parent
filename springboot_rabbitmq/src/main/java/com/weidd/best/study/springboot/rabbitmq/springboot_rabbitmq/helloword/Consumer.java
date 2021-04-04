package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.helloword;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 16:08
 **/
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取连接工厂对象
       /* ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/rabbitmqtest1");
        factory.setUsername("rabbitmqtest1user");
        factory.setPassword("rabbitmqtest1u);

        Connection connection = factory.newConnection();*/
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        // 通道与队列建立连接
        channel.queueDeclare("rabbitmqtestqueue1", true, false, false, null);

        //消费消息
        // 参数1:消费哪个队列的消息(队列名称)
        // 参数2:开启消息的自动确认机制
        // 参数3:消费时的回调
        channel.basicConsume("rabbitmqtestqueue1", true, new DefaultConsumer(channel) {
            @Override //最后一个参数 :消息队列中取出的消息
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("接收到消息:--> "+new String(body));

            }
        });

        // consumer 端不建议关闭连接和通道(否则可能会接受不到回调,就结束了.)
//        channel.close();
//        connection.close();

    }
}
