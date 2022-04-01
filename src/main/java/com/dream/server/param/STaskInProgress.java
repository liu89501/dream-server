package com.dream.server.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterSaver;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STaskInProgress implements ParameterSaver
{
    private long ptid;

    private PConditionList taskCondition;

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(ptid);
        taskCondition.save(packet);
    }
}
