package com.dream.server.param;

import lombok.Data;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterLoader;

@Data
public class LQuestActionInfo implements ParameterLoader
{
    private LQuestAction questAction;

    private int conditionID;

    public boolean isValid()
    {
        return questAction != null;
    }

    @Override
    public void load(Packet packet)
    {
        conditionID = packet.readInt();

        questAction = switch (conditionID)
                {
                    case EConditionID.KILL_TARGET -> new LQuestActionKillTarget();
                    case EConditionID.EVENT_TRIGGER -> new LQuestActionEvent();
                    default -> null;
                };

        if (questAction != null)
        {
            questAction.load(packet);
        }
    }
}
