package com.dream.server.cache;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EhCache
{
    String value();
}
