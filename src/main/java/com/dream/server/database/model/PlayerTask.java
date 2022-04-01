package com.dream.server.database.model;

import com.dream.server.param.PConditionList;
import com.dream.server.param.PItemList;
import lombok.Data;

import java.io.Serializable;

/**
 * player_task
 */
@Data
public class PlayerTask implements Serializable
{
    private Long ptid;

    private Byte taskState;

    private Integer taskId;

    private Integer groupId;

    private Integer playerId;

    private Boolean taskTracking;

    private String descAsset;

    private PConditionList taskCondition;

    private PItemList taskReward;
}