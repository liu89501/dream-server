package com.dream.server.settings;

import com.dream.container.anno.*;
import com.dream.server.config.Constant;
import com.dream.server.mq.PlayerMessageConsumer;
import com.dream.server.utils.Logs;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

@Component(proxy = false, instant = true)
public class ToolsTC
{
    @Assign
    private PlayerMessageConsumer playerMessageConsumer;

    @LaunchArg("-mq_addr")
    private String rocketMQAddress;


    @ToContainer
    public MQProducer producer() throws MQClientException
    {
        DefaultMQProducer producer = new DefaultMQProducer("dream-producer");
        producer.setNamesrvAddr(rocketMQAddress);
        producer.setRetryAnotherBrokerWhenNotStoreOK(true);
        //producer.start();

        Logs.LOG.info("MQProducer Initialized");
        return producer;
    }

    //@Exec
    public void playerMessageConfig() throws MQClientException
    {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("player-message");
        pushConsumer.registerMessageListener(playerMessageConsumer);
        pushConsumer.setNamesrvAddr(rocketMQAddress);
        pushConsumer.subscribe(Constant.MQ_TOPIC_DIALOG, "*");
        pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        pushConsumer.start();
    }
}
