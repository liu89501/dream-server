package com.dream.server.database.model;

import java.io.Serializable;
import java.util.Date;

/**
 * game_player
 */
public class Player implements Serializable
{
    private Integer playerId;

    private String platformAccountId;

    private Integer playerLevel;

    private Integer playerExp;

    private Date lastLoginDate;

    private Long learnedTalents;

    private Integer maxExp;

    private String loginHost;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getPlatformAccountId() {
        return platformAccountId;
    }

    public void setPlatformAccountId(String platformAccountId) {
        this.platformAccountId = platformAccountId;
    }

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(Integer playerLevel) {
        this.playerLevel = playerLevel;
    }

    public Integer getPlayerExp() {
        return playerExp;
    }

    public void setPlayerExp(Integer playerExp) {
        this.playerExp = playerExp;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Long getLearnedTalents() {
        return learnedTalents;
    }

    public void setLearnedTalents(Long learnedTalents) {
        this.learnedTalents = learnedTalents;
    }

    public Integer getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(Integer maxExp) {
        this.maxExp = maxExp;
    }

    public String getLoginHost() {
        return loginHost;
    }

    public void setLoginHost(String loginHost) {
        this.loginHost = loginHost;
    }
}