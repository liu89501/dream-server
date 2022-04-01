package com.dream.server.database.model;

/**
 * player_material
 */
public class PlayerMaterial
{
    private Long pmId;

    private Integer itemGuid;

    private Integer num;

    private Integer playerId;

    public PlayerMaterial(Integer itemGuid, Integer num)
    {
        this.itemGuid = itemGuid;
        this.num = num;
    }

    public PlayerMaterial()
    {
    }

    public Long getPmId() {
        return pmId;
    }

    public void setPmId(Long pmId) {
        this.pmId = pmId;
    }

    public Integer getItemGuid() {
        return itemGuid;
    }

    public void setItemGuid(Integer itemGuid) {
        this.itemGuid = itemGuid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}