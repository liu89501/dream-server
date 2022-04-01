package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.config.Constant;
import com.dream.server.mq.MessageResponse;
import com.dream.server.mq.NullSendCallback;
import com.dream.server.mq.PlayerMessageContainer;
import com.dream.server.mq.PlayerMessageDesc;
import com.dream.server.param.PPlayerMessage;
import com.dream.server.param.PacketNetty;
import com.dream.server.utils.Logs;
import com.dream.server.utils.PacketUtils;
import com.dream.service.codec.ParameterLoader;
import com.mongodb.client.MongoDatabase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;

import java.lang.reflect.Parameter;

@Component(proxy = false)
public class CPlayerNotifier
{
    @Assign
    private MQProducer producer;

    @Assign
    private CPlayerDataCache playerDataCache;

    @Assign
    private CChannels channels;

    @Assign
    private MongoDatabase mongoDatabase;

    @Assign
    private CPlayer cPlayer;

    @Assign
    private PlayerMessageContainer playerMessageContainer;

    public void send(PPlayerMessage playerMessage) throws Exception
    {
        if (channels.isValid(playerMessage.getPlayerId()))
        {
            receive(playerMessage);
        }
        else
        {
            String instanceUID = cPlayer.getPlayerCurrentServerInstanceUID(playerMessage.getPlayerId());

            Message message = new Message(Constant.MQ_TOPIC_DIALOG, instanceUID, PacketUtils.encodeToBytes(playerMessage));
            producer.send(message, NullSendCallback.SINGLETON);
        }
    }

    public void receive(PPlayerMessage playerMessage) throws Exception
    {
        if (!channels.isValid(playerMessage.getPlayerId()))
        {
            Logs.LOG.warn("指定玩家不存在: {}", playerMessage.getPlayerId());
            return;
        }

        PlayerMessageDesc playerMessageDesc = playerMessageContainer.getPlayerMessageDesc(playerMessage.getType());

        Parameter[] parameters = playerMessageDesc.method().getParameters();

        Object[] args = new Object[parameters.length];

        for (int i = 0; i < parameters.length; i++)
        {
            Class<?> type = parameters[i].getType();

            if (ParameterLoader.class.isAssignableFrom(type))
            {
                ParameterLoader paramInstance = (ParameterLoader)type.getConstructor().newInstance();
                paramInstance.load(new PacketNetty(Unpooled.wrappedBuffer(playerMessage.getData())));
                args[i] = paramInstance;
            }
            else if (ByteBuf.class == type)
            {
                args[i] = Unpooled.wrappedBuffer(playerMessage.getData());
            }
        }

        Object returnObject = playerMessageDesc.method().invoke(playerMessageDesc.instance(), args);

        if (returnObject != null)
        {
            if (returnObject instanceof MessageResponse messageResponse)
            {
                channels.writeMessage(playerMessage.getPlayerId(), messageResponse.getServiceMark(), messageResponse.getParam());
            }
        }
    }
}
