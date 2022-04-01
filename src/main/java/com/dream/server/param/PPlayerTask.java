package com.dream.server.param;

import com.dream.server.database.model.PlayerTask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.dream.service.codec.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PPlayerTask extends ParameterArchive
{
    private long ptid;

    private int groupId;

    private String descAsset;

    private byte taskState;

    private boolean taskTracking;

    private PConditionList taskCondition;

    private PItemList taskReward;

    public PPlayerTask(PlayerTask task)
    {
        ptid = task.getPtid();
        groupId = task.getGroupId();
        descAsset = task.getDescAsset();
        taskState = task.getTaskState();
        taskTracking = task.getTaskTracking();
        taskCondition = task.getTaskCondition();
        taskReward = task.getTaskReward();
    }

    public PlayerTask toPlayerTask()
    {
        PlayerTask task = new PlayerTask();
        task.setPtid(ptid);
        task.setGroupId(groupId);
        task.setDescAsset(descAsset);
        task.setTaskState(taskState);
        task.setTaskTracking(taskTracking);
        task.setTaskCondition(taskCondition);
        task.setTaskReward(taskReward);
        return task;
    }

    @Override
    public void load(Packet packet)
    {
        ptid = packet.readLong();
        groupId = packet.readInt();
        descAsset = packet.readString();
        taskState = packet.readByte();
        taskTracking = packet.readBool();

        taskCondition = new PConditionList();
        taskCondition.load(packet);

        taskReward = new PItemList();
        taskReward.load(packet);
    }

    @Override
    public void save(Packet packet)
    {
        packet.writeLong(ptid);
        packet.writeInt(groupId);
        packet.writeString(descAsset);
        packet.writeByte(taskState);
        packet.writeBool(taskTracking);
        taskCondition.save(packet);
        taskReward.save(packet);
    }
}
