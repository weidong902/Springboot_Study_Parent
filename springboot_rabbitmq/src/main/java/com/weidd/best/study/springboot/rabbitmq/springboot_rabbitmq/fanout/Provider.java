package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 20:14
 **/
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道
        Channel channel = connection.createChannel();

        // 参数: 参数1: 交换机的名称, 参数2 : 交换机的类型: fanout: 广播类型
        channel.exchangeDeclare("logs", "fanout");

        //发送消息
        channel.basicPublish("logs","", MessageProperties.PERSISTENT_TEXT_PLAIN,"fanout Message".getBytes());

        RabbitMQConnectUtils.closeChannelAndConnection(channel,connection);

    }
}
