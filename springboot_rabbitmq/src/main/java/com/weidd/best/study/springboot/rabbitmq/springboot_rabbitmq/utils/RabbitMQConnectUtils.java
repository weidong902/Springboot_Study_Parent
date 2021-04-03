package com.weidd.best.study.springboot.rabbitmq.springboot_rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 16:48
 **/
public class RabbitMQConnectUtils {
    private static final ConnectionFactory factory;

    static {
        // 重量级对象,在类加载时只进行一次赋值.
        factory = new ConnectionFactory();
    }

    // 提供创建连接的方法
    public static Connection getRabbitMQConnection() {
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/rabbitmqtest1");
        factory.setUsername("rabbitmqtest1user");
        factory.setPassword("rabbitmqtest1user");
        try {
            return factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道关闭连接
    public static void closeChannelAndConnection(Channel channel, Connection connection) {
        try {
            if (null != channel) channel.close();
            if (null != connection) connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
