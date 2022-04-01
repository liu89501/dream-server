package com.dream.server.mq;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.Transaction;
import com.dream.server.database.mapper.PlayerTaskMapper;
import com.dream.server.utils.Logs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

@Component
public class PlayerTaskUpdateConsumer implements MessageListenerConcurrently
{

    @Assign
    private PlayerTaskMapper playerTaskMapper;

    @Transaction(batch = true)
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext)
    {
        try
        {
            for (MessageExt messageExt : list)
            {
                ByteBuf byteBuf = Unpooled.wrappedBuffer(messageExt.getBody());

                //List<PlayerTask> tasks = container.getTasks();

                /*if (MyUtils.isValidCollection(tasks))
                {
                    for (PlayerTask task : tasks)
                    {
                        Logs.LOG.info("接受任务更新消息: {}", task);
                        playerTaskMapper.updateByPrimaryKeySelective(task);
                    }
                }
                else
                {
                    Logs.LOG.warn("解码任务更新消息失败");
                }*/
            }
        }
        catch (Exception e)
        {
            Logs.LOG.error("消费消息失败", e);
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
