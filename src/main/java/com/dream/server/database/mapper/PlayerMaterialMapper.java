package com.dream.server.database.mapper;

import com.dream.server.database.model.PlayerMaterialExample;
import com.dream.server.database.model.PlayerMaterial;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlayerMaterialMapper {
    long countByExample(PlayerMaterialExample example);

    int deleteByExample(PlayerMaterialExample example);

    int deleteByPrimaryKey(Long pmId);

    int insert(PlayerMaterial record);

    int insertSelective(PlayerMaterial record);

    List<PlayerMaterial> selectByExample(PlayerMaterialExample example);

    PlayerMaterial selectByPrimaryKey(Long pmId);

    int updateByExampleSelective(@Param("record") PlayerMaterial record, @Param("example") PlayerMaterialExample example);

    int updateByExample(@Param("record") PlayerMaterial record, @Param("example") PlayerMaterialExample example);



    int updateByPrimaryKeySelective(PlayerMaterial record);

    int updateByPrimaryKey(PlayerMaterial record);
}