package com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.workqueue;

import com.rabbitmq.client.*;
import com.weidd.best.study.springboot.rabbitmq.rabbitmq_basics.utils.RabbitMQConnectUtils;

import java.io.IOException;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-03 17:56
 **/
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQConnectUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1);//每次只能消费一个消息
        //通道与队列建立连接
        channel.queueDeclare("workqueue", true, false, false, null);

        // 参数:参数2:自动确认 true时, 消费者自动向rabbitmq确认消息消费. false 不会自动确认
        channel.basicConsume("workqueue", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                try {
                    Thread.sleep(1000); //由于工作队列默认的是对每个消费则平均分配任务,模拟其中一个处理较慢,是否还是平均分配
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Consumer1 获取到工作消息--> " + new String(body));
                //手动确认 参数1:手动确认消息标识(确认队列中哪个具体的消息) ,参数2:false,每次确认一个
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        });

    }
}
