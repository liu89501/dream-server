package com.dream.server.mq;

import java.lang.annotation.*;

@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HandlerUnit
{
    int value();
}
