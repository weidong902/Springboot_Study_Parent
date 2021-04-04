package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 10:51
 * <p>
 * rabbitmq 模型5 topics
 **/
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道Channel
        Channel channel = connection.createChannel();
        //交换机和通道绑定
        String exchangeName="logs_topic";
        channel.exchangeDeclare(exchangeName, "topic");
        //发布消息
        String routingKey = "user.save.aaa";//路由key
        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ("模型5基于动态路由的方式发布消息RoutingKey-> " + routingKey + "<").getBytes());
        RabbitMQConnectUtils.closeChannelAndConnection(channel, connection);
    }
}
