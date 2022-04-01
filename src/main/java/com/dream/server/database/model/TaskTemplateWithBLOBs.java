package com.dream.server.database.model;

import com.dream.server.param.PConditionList;
import com.dream.server.param.PItemList;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * task_template
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TaskTemplateWithBLOBs extends TaskTemplate
{
    private PConditionList taskCondition;

    private PItemList taskReward;
}