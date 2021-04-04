package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.dirct;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils.RabbitMQConnectUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 14:53
 **/
public class Provider {
    //生产消息

    @Test
    public void sendMesssge() throws IOException, TimeoutException {
        /*//创建连接mq的工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接mq的主机
        connectionFactory.setHost("localhost");
        // 设置端口 5672
        connectionFactory.setPort(5672);
        //设置连接的虚拟主机
        connectionFactory.setVirtualHost("/rabbitmqtest1");
        //设置用户名和密码
        connectionFactory.setUsername("rabbitmqtest1user");
        connectionFactory.setPassword("rabbitmqtest1user");

        //获取连接对象
        Connection connection = connectionFactory.newConnection();*/
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        // 参数1:队列名称(如果首次会自动新建名称)
        // 参数2:durable是否持久化 false 不持久话. true 持久化
        // 参数3: exclusive 是否独占队列 false不独占 ,true 独占队列
        // 参数4: autoDelete 消息消费完后是否被删除(队列的删除) true 删除 ,false 不删除
        // 参数5: 其他参数
        channel.queueDeclare("rabbitmqtestqueue1", true, false, false, null);
        //发布消息
        // 参数1:交换机(简单模式没有交换机) 参数2: 队列名称 参数3:传递消息额外设置(MessageProperties.PERSISTENT_TEXT_PLAIN:消息持久化到硬盘中) 参数4:消息的具体内容
        channel.basicPublish("", "rabbitmqtestqueue1", MessageProperties.PERSISTENT_TEXT_PLAIN, "你好".getBytes());

        /*channel.close();
        connection.close();*/
        RabbitMQConnectUtils.closeChannelAndConnection(channel,connection);
    }
}

