package com.dream.server.settings;

import com.dream.container.anno.Config;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.nio.charset.Charset;
import java.util.UUID;

@Config(classpath = "settings-dream.json")
public class ProjectSettings
{
    @JsonProperty("server-token")
    private String serverToken;

    @JsonIgnore
    private String instanceUID;

    @JsonProperty("server-application-path")
    private String serverApplicationPath;

    @JsonProperty("string-param-charset")
    private Charset stringParameterCharset;

    public ProjectSettings()
    {
        instanceUID = UUID.randomUUID().toString();
    }

    public String getServerToken()
    {
        return serverToken;
    }

    public void setServerToken(String serverToken)
    {
        this.serverToken = serverToken;
    }

    public String getInstanceUID()
    {
        return instanceUID;
    }

    public String getServerApplicationPath()
    {
        return serverApplicationPath;
    }

    public void setServerApplicationPath(String serverApplicationPath)
    {
        this.serverApplicationPath = serverApplicationPath;
    }

    public Charset getStringParameterCharset()
    {
        return stringParameterCharset;
    }

    public void setStringParameterCharset(Charset stringParameterCharset)
    {
        this.stringParameterCharset = stringParameterCharset;
    }
}
