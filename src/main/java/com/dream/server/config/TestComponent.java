package com.dream.server.config;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Exec;
import com.dream.container.anno.Transaction;
import com.dream.server.database.mapper.PlayerMapper;
import com.dream.server.database.model.Player;

public class TestComponent extends TestAbstractClass
{
    private PlayerMapper playerMapper;

    @Assign
    private TestComponent2 component2;

    @Transaction
    @Exec
    public void testTransaction()
    {
        Player player = new Player();
        player.setPlayerId(14);
        playerMapper.updateByPrimaryKeySelective(player);

        component2.testTransaction();
    }
}
