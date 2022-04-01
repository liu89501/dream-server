package com.dream.server.database.model;

import java.io.Serializable;
import java.util.Date;

/**
 * server_info
 * @author 
 */
public class ServerInfo implements Serializable {
    private String serverId;

    private String serverAddr;

    private String mapName;

    private String gameMode;

    private Integer activePlayers;

    private Integer maxPlayers;

    private Integer activeState;

    private Date createDate;

    private static final long serialVersionUID = 1L;

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerAddr() {
        return serverAddr;
    }

    public void setServerAddr(String serverAddr) {
        this.serverAddr = serverAddr;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public Integer getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(Integer activePlayers) {
        this.activePlayers = activePlayers;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Integer getActiveState() {
        return activeState;
    }

    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}