package com.dream.server.param;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.dream.service.codec.Packet;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "impl"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PConditionEvent.class, name = "evt"),
        @JsonSubTypes.Type(value = PConditionKillTarget.class, name = "kt"),
        @JsonSubTypes.Type(value = PConditionAssociatedTask.class, name = "ast")
})
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class PCondition extends ParameterArchive
{
    private int conditionID;

    private boolean completed;

    public static PCondition build(Packet buffer)
    {
        int conditionID = buffer.readInt();

        PCondition condition = switch (conditionID)
                {
                    case EConditionID.KILL_TARGET -> new PConditionKillTarget();
                    case EConditionID.EVENT_TRIGGER -> new PConditionEvent();
                    case EConditionID.ASSOC_TASK -> new PConditionAssociatedTask();
                    default -> null;
                };

        if (condition != null)
        {
            condition.setConditionID(conditionID);
        }

        return condition;
    }

    @Override
    public void load(Packet packet)
    {
        completed = packet.readBool();
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeInt(conditionID);
        packet.writeBool(completed);
    }
}
