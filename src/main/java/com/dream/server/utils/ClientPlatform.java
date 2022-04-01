package com.dream.server.utils;

public enum ClientPlatform
{
    STEAM("steam"),
    EPIC("epic"),
    XBOBX("xbox"),
    PS4("ps4");

    String platform;

    ClientPlatform(String platform)
    {
        this.platform = platform;
    }

    public boolean equal(String platformName)
    {
        return platform.equalsIgnoreCase(platformName);
    }
}
