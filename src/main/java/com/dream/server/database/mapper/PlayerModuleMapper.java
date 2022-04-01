package com.dream.server.database.mapper;

import com.dream.server.database.model.PlayerModuleExample;
import com.dream.server.database.model.PlayerModule;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlayerModuleMapper {
    long countByExample(PlayerModuleExample example);

    int deleteByExample(PlayerModuleExample example);

    int deleteByPrimaryKey(Long moduleId);

    int insert(PlayerModule record);

    List<PlayerModule> selectByExampleWithBLOBs(PlayerModuleExample example);

    List<PlayerModule> selectByExample(PlayerModuleExample example);

    PlayerModule selectByPrimaryKey(Long moduleId);

    int updateByExampleSelective(@Param("record") PlayerModule record, @Param("example") PlayerModuleExample example);

    int updateByPrimaryKeySelective(PlayerModule record);
}