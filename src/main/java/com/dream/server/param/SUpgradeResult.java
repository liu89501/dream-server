package com.dream.server.param;

import com.dream.server.utils.PacketUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;
import com.dream.service.codec.ParameterSaver;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SUpgradeResult implements ParameterSaver
{
    private boolean upgradeSuccess;

    private List<PPlayerMaterial> taskCondition;

    @Override
    public void save(Packet packet)
    {
        packet.writeBool(upgradeSuccess);
        PacketUtils.saveList(taskCondition, packet);
    }
}
