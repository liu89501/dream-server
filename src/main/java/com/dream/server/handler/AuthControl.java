package com.dream.server.handler;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.server.service.component.CChannels;
import com.dream.service.auth.AuthenticateControl;
import com.dream.service.auth.AuthenticatedInfo;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


@Component(proxy = false)
public class AuthControl implements AuthenticateControl
{
    @Assign
    private CChannels channels;

    @Assign
    private MongoDatabase mongo;

    @Override
    public void OnLogout(AuthenticatedInfo authenticatedInfo)
    {
        channels.remove(authenticatedInfo.getPlayerId());

        mongo.getCollection("Players")
                .deleteOne(Filters.eq("player_id", authenticatedInfo.getPlayerId()));
    }
}
