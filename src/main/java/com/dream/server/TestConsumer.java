package com.dream.server;

import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.client.log.ClientLogger;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class TestConsumer
{
    public static void main(String[] args) throws Exception
    {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");

        DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("test-consumer");
        consumer.setNamesrvAddr("192.168.1.106:9876");
        consumer.subscribe("TestTopic", "TagA");
        consumer.setPullBatchSize(1);
        consumer.start();

        new Thread(() -> {

            List<MessageExt> poll = consumer.poll();

            for (MessageExt messageExt : poll)
            {
                System.out.println(new String(messageExt.getBody()));
            }

            System.out.println("线程1---------");

        }).start();

        new Thread(() -> {

            List<MessageExt> poll = consumer.poll();

            for (MessageExt messageExt : poll)
            {
                System.out.println(new String(messageExt.getBody()));
            }

            System.out.println("线程2---------");

        }).start();

        //System.out.println("end");


    }
}
