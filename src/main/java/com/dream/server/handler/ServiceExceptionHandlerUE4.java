package com.dream.server.handler;

import com.dream.container.anno.Component;
import com.dream.service.ServiceExceptionHandler;
import com.dream.service.codec.ParameterSaver;
import com.dream.service.codec.SuccessOrFailure;

import java.lang.reflect.Method;

@Component(proxy = false, instant = true)
public class ServiceExceptionHandlerUE4 implements ServiceExceptionHandler
{
    @Override
    public Object handleException(Throwable throwable, Method method)
    {
        if (ParameterSaver.class.isAssignableFrom(method.getReturnType()))
        {
            return SuccessOrFailure.FAIL;
        }

        return null;
    }
}
