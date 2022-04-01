package com.dream.server.settings;

import com.dream.container.anno.Config;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Config(classpath = "settings-mongodb.json")
public class MongodbSettings
{
    @JsonProperty("connect-string")
    private String connectString;
}
