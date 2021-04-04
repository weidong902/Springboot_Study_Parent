package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 17:48
 * 工作队列:rabbitmq 第二种模型
 **/

public class Provider {

    @Test
    public void sendMessage() throws IOException {
        //获取连接对象
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("workqueue", true, false, false, null);
        for (int i = 0; i < 10; i++) {
            //生产消息
            channel.basicPublish("", "workqueue", MessageProperties.PERSISTENT_TEXT_PLAIN, (i + "< > 你好工作队列").getBytes());
        }
        RabbitMQConnectUtils.closeChannelAndConnection(channel, connection);
    }

}
