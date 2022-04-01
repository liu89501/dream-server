package com.dream.server.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public abstract class WANIPUtils
{
    private static String WAN_IP;

    /**
     * 获取当前机器的公网IP, 由于服务器的公网IP是没法通过操作系统自身获取的, 所以这里向公网的服务器发送一个HTTP请求
     * @return  公网IP
     */
    public static String getHostWANIP()
    {
        if (WAN_IP == null)
        {
            try
            {
                HttpClient httpClient = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("https://ip.tool.lu"))
                        .GET()
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
                String content = response.body();
                int splitBegin = content.indexOf(' ') + 1;
                int splitEnd = content.indexOf('\n');
                WAN_IP = content.substring(splitBegin, splitEnd);
            }
            catch (Exception ignored)
            {
            }
        }
        return WAN_IP;
    }
}
