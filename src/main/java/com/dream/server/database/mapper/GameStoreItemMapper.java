package com.dream.server.database.mapper;

import com.dream.server.database.model.GameStoreItem;
import com.dream.server.database.model.GameStoreItemExample;
import com.dream.server.database.model.GameStoreItemWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GameStoreItemMapper {
    long countByExample(GameStoreItemExample example);

    int deleteByExample(GameStoreItemExample example);

    int deleteByPrimaryKey(Long gsiId);

    int insert(GameStoreItemWithBLOBs record);

    int insertSelective(GameStoreItemWithBLOBs record);

    List<GameStoreItemWithBLOBs> selectByExampleWithBLOBs(GameStoreItemExample example);

    List<GameStoreItem> selectByExample(GameStoreItemExample example);

    GameStoreItemWithBLOBs selectByPrimaryKey(Long gsiId);

    int updateByExampleSelective(@Param("record") GameStoreItemWithBLOBs record, @Param("example") GameStoreItemExample example);

    int updateByExampleWithBLOBs(@Param("record") GameStoreItemWithBLOBs record, @Param("example") GameStoreItemExample example);

    int updateByExample(@Param("record") GameStoreItem record, @Param("example") GameStoreItemExample example);

    int updateByPrimaryKeySelective(GameStoreItemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GameStoreItemWithBLOBs record);

    int updateByPrimaryKey(GameStoreItem record);
}