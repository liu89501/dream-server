package com.dream.server.sync;

import java.lang.annotation.*;

@Inherited
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sync
{
    /**
     * category
     * @return  Sync category
     */
    String value();
}
