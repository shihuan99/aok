/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
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