package com.dream.server.config;

import com.dream.container.anno.Transaction;
import com.dream.server.database.mapper.PlayerMapper;
import com.dream.server.database.model.Player;

public class TestComponent2
{
    private PlayerMapper playerMapper;

    @Transaction
    public void testTransaction()
    {
        Player player = new Player();
        player.setPlayerId(14);
        player.setPlatformAccountId("hahaha");
        playerMapper.updateByPrimaryKeySelective(player);

        throw new RuntimeException();
    }
}
