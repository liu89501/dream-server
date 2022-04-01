package com.dream.server.database.mapper;

import com.dream.server.database.model.PlayerWeapon;
import com.dream.server.database.model.PlayerWeaponExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlayerWeaponMapper {
    long countByExample(PlayerWeaponExample example);

    int deleteByExample(PlayerWeaponExample example);

    int deleteByPrimaryKey(Long weaponId);

    int insert(PlayerWeapon record);

    List<PlayerWeapon> selectByExampleWithBLOBs(PlayerWeaponExample example);

    List<PlayerWeapon> selectByExample(PlayerWeaponExample example);

    PlayerWeapon selectByPrimaryKey(Long weaponId);

    int updateByExampleSelective(@Param("record") PlayerWeapon record, @Param("example") PlayerWeaponExample example);

    int updateByPrimaryKeySelective(PlayerWeapon record);
}