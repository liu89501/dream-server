package com.dream.server.database.mapper;

import com.dream.server.database.model.TaskTemplateExample;
import com.dream.server.database.model.TaskTemplate;
import com.dream.server.database.model.TaskTemplateWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TaskTemplateMapper {
    long countByExample(TaskTemplateExample example);

    int deleteByExample(TaskTemplateExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(TaskTemplateWithBLOBs record);

    int insertSelective(TaskTemplateWithBLOBs record);

    List<TaskTemplateWithBLOBs> selectByExampleWithBLOBs(TaskTemplateExample example);

    List<TaskTemplate> selectByExample(TaskTemplateExample example);

    TaskTemplateWithBLOBs selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") TaskTemplateWithBLOBs record, @Param("example") TaskTemplateExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskTemplateWithBLOBs record, @Param("example") TaskTemplateExample example);

    int updateByExample(@Param("record") TaskTemplate record, @Param("example") TaskTemplateExample example);

    int updateByPrimaryKeySelective(TaskTemplateWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TaskTemplateWithBLOBs record);

    int updateByPrimaryKey(TaskTemplate record);
}