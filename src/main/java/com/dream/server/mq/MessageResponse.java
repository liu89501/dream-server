package com.dream.server.mq;

import com.dream.service.codec.ParameterSaver;
import lombok.Data;

@Data
public class MessageResponse
{
    private ParameterSaver param;

    private int serviceMark;

    public static MessageResponse build(int serviceMark, ParameterSaver param)
    {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.param = param;
        messageResponse.serviceMark = serviceMark;
        return messageResponse;
    }
}
