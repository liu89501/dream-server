package com.dream.server.sync;

import com.dream.container.ProxyMethodPostProcessor;
import com.dream.container.ProxyPostProcessArgs;
import com.dream.container.anno.MethodPostProcessor;


@MethodPostProcessor
public class SynchronizerMethodPostProcessor implements ProxyMethodPostProcessor
{
    @Override
    public void postProcess(ProxyPostProcessArgs args)
    {
        SynchronizerDeferredArgs deferredArgs = Synchronizer.deferredMarks.get();
        if (deferredArgs != null)
        {
            deferredArgs.sync().immediatelyReleaseSyncMark(deferredArgs.key());
        }
    }
}
