package com.dream.server.database.mapper;

import com.dream.server.database.model.PlayerTask;
import com.dream.server.database.model.PlayerTaskExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlayerTaskMapper {
    long countByExample(PlayerTaskExample example);

    int deleteByExample(PlayerTaskExample example);

    int deleteByPrimaryKey(Long ptid);

    int insert(PlayerTask record);

    int insertSelective(PlayerTask record);

    List<PlayerTask> selectByExampleWithBLOBs(PlayerTaskExample example);

    List<PlayerTask> selectByExample(PlayerTaskExample example);

    PlayerTask selectByPrimaryKey(Long ptid);

    int updateByExampleSelective(@Param("record") PlayerTask record, @Param("example") PlayerTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") PlayerTask record, @Param("example") PlayerTaskExample example);

    int updateByExample(@Param("record") PlayerTask record, @Param("example") PlayerTaskExample example);

    int updateByPrimaryKeySelective(PlayerTask record);

    int updateByPrimaryKeyWithBLOBs(PlayerTask record);

    int updateByPrimaryKey(PlayerTask record);
}