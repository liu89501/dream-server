package com.dream.server.service.component;

import com.dream.container.anno.Component;
import com.dream.container.anno.LaunchArg;
import com.dream.server.utils.Authenticate;
import com.dream.server.utils.Logs;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Component(proxy = false)
public class CSteamAPI
{
    /**
     * Steam Web API 发行商密钥
     */
    @LaunchArg("-web_api_key")
    private String webAPIKey;

    /**
     * Steam AppID
     */
    @LaunchArg("-app_id")
    private int appId;

    /**
     * Steam 用户校验 API 地址
     */
    private static String Authenticate_API = "https://api.steampowered.com/ISteamUserAuth/AuthenticateUserTicket/v1/";


    @SuppressWarnings("unchecked")
    public Authenticate authenticate(String ticket)
    {
        Map<String, Object> playerSummaries = authenticateUserTicket(ticket);
        Authenticate authenticate = new Authenticate();
        authenticate.setAuthorized(false);

        if (playerSummaries == null)
        {
            authenticate.setErrorMessage("Steamworks校验错误");
            return authenticate;
        }
        Map<String, Object> response = (Map<String, Object>)playerSummaries.get("response");

        Map<String, Object> error = (Map<String, Object>)response.get("error");
        if (error != null)
        {
            authenticate.setErrorMessage(String.valueOf(error.get("errordesc")));
            return authenticate;
        }

        Map<String, Object> params = (Map<String, Object>)response.get("params");

        String result = String.valueOf(params.get("result"));
        boolean publisherbanned = (Boolean) params.get("publisherbanned");
        boolean vacbanned = (Boolean) params.get("vacbanned");

        if (!"OK".equals(result))
        {
            authenticate.setErrorMessage("authenticate failure");
            return authenticate;
        }

        if (publisherbanned || vacbanned)
        {
            authenticate.setErrorMessage("You're banned");
            return authenticate;
        }

        authenticate.setAccountId(String.valueOf(params.get("steamid")));
        authenticate.setAuthorized(true);

        return authenticate;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> authenticateUserTicket(String playerTicket)
    {
        Logs.LOG.info("getPlayerSummaries ticket: {}", playerTicket);

        try
        {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(String.format("%s?key=%s&appid=%s&ticket=%s", Authenticate_API, webAPIKey, appId, playerTicket)))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

            Logs.LOG.info(responseBody);

            JsonMapper mapper = new JsonMapper();
            return mapper.readValue(responseBody, Map.class);
        }
        catch (Exception e)
        {
            Logs.LOG.error("Steam用户校验错误", e);
        }

        return null;
    }
}
