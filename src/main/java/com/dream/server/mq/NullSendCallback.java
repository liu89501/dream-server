package com.dream.server.mq;

import com.dream.server.utils.Logs;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

public class NullSendCallback implements SendCallback
{
    public static final NullSendCallback SINGLETON = new NullSendCallback();

    @Override
    public void onSuccess(SendResult sendResult)
    {
        Logs.LOG.info("message send successfully, {}", sendResult);
    }

    @Override
    public void onException(Throwable e)
    {
        Logs.LOG.error("failed to send message ", e);
    }
}
