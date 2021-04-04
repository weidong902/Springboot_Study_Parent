package com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.routdirect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 09:08
 * <p>
 * 路由模式
 **/
public class Provider {

    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        //绑定交换机
        channel.exchangeDeclare(exchangeName, "direct");
        //路由模式:需要routingKey
        String routingKey = "error";
        // 参数1:交换机名 参数2:路由key 参数3:持久化消息. 参数4:真正要发送的消息
        channel.basicPublish(exchangeName, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, ("这是路由模式下的rabbitmq消息[" + routingKey + "]请接收").getBytes());
        //关闭资源
        RabbitMQConnectUtils.closeChannelAndConnection(channel, connection);


    }
}
