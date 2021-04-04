package com.weidd.best.study.springboot.rabbitmq.rabbitmq_springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: Springboot_Study_Parent
 * @author: weidd
 * @date: 2021-04-04 15:31
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRabbitmqApplication.class)
public class RabbitMQTest {

    @Autowired
    private RabbitTemplate rabbitTemplate; //springboot整合rabbitmq使用RabbitTemplate操作.

    // topics 动态路由模型
    @Test
    public void topicsProvider() {
        rabbitTemplate.convertSendAndReceive("topicsRouting", "user.save.123"," topics 动态路由模型消息 ");

    }
    // routing 路由模型
    @Test
    public void routingProvider() {
        rabbitTemplate.convertSendAndReceive("directRouting", "error"," routing 路由 模型消息 ");

    }

    // fanout 广播模型
    @Test
    public void fanoutProvider() {
        rabbitTemplate.convertSendAndReceive("logsExchange", ""," fanout 模型消息 ");

    }

    // work queue
    @Test
    public void workProvider() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertSendAndReceive("workqueue", i + " <--WorkQueue 模型消息");
        }

    }

    //hello world
    @Test
    public void test1() {
        //此时不会创建队列的
        rabbitTemplate.convertAndSend("helloworld", "hello world模式1使用rabbitTemplate发送的消息");

    }
}
