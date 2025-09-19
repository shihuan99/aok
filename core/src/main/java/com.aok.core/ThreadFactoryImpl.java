package com.aok.core;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class ThreadFactoryImpl implements ThreadFactory {

    private final AtomicLong index = new AtomicLong(0);

    private final String prefix;

    public ThreadFactoryImpl(final String threadNamePrefix) {
        this.prefix = threadNamePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, prefix + this.index.incrementAndGet());
        thread.setUncaughtExceptionHandler(
            (t, e) -> log.error("[BUG] Thread has an uncaught exception, threadId={}, threadName={}", t.getId(), t.getName(), e)
        );
        return thread;
    }
}