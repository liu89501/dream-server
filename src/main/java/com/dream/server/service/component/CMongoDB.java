package com.dream.server.service.component;

import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.ToContainer;
import com.dream.server.settings.MongodbSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

@Component(proxy = false, instant = true)
public class CMongoDB
{
    @Assign
    private MongodbSettings mongodbSettings;

    @ToContainer(uid = "Dev")
    public MongoDatabase mongoDatabase()
    {
        MongoClient mongoClient = MongoClients.create(mongodbSettings.getConnectString());
        return mongoClient.getDatabase("dev");
    }
}
