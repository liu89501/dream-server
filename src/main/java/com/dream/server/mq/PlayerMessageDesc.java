package com.dream.server.mq;

import java.lang.reflect.Method;

public record PlayerMessageDesc(int mark, Method method, Object instance)
{
}
