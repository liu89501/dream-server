package com.dream.server.mq;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.param.PPlayerMessage;
import com.dream.server.service.component.CPlayerNotifier;
import com.dream.server.utils.Logs;
import com.dream.server.utils.PacketUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

@Component(proxy = false)
public class PlayerMessageConsumer implements MessageListenerConcurrently
{
    @Assign
    private CPlayerNotifier playerNotifier;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context)
    {
        try
        {
            for (MessageExt msg : msgs)
            {
                PPlayerMessage mqDialog = new PPlayerMessage();
                PacketUtils.decodeFromBytes(mqDialog, msg.getBody());
                playerNotifier.receive(mqDialog);
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        catch (Exception e)
        {
            Logs.LOG.error("PlayerMessageConsumer", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }
    }
}
