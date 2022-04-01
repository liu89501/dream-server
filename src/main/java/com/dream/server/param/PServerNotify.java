package com.dream.server.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ScheduledFuture;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PServerNotify
{
    private int playerId;

    private ScheduledFuture<?> serverTimerHandle;
}
