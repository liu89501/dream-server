package com.dream.server.database.model;

import java.io.Serializable;

/**
 * task_template
 */
public class TaskTemplate implements Serializable {
    private Integer taskId;

    private String taskDescAsset;

    private Byte taskType;

    private Integer groupId;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescAsset() {
        return taskDescAsset;
    }

    public void setTaskDescAsset(String taskDescAsset) {
        this.taskDescAsset = taskDescAsset;
    }

    public Byte getTaskType() {
        return taskType;
    }

    public void setTaskType(Byte taskType) {
        this.taskType = taskType;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}