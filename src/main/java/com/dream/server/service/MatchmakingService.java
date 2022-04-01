package com.dream.server.service;

import com.dream.server.param.LLaunchServer;
import com.dream.service.anno.Service;
import com.dream.service.codec.SuccessOrFailure;


@Service
public class MatchmakingService
{
    public SuccessOrFailure startMatchmaking(LLaunchServer param)
    {


        return SuccessOrFailure.SUCCESS;
    }
}
