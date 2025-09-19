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

import org.apache.qpid.server.bytebuffer.QpidByteBuffer;
import org.apache.qpid.server.bytebuffer.SingleQpidByteBuffer;
import org.apache.qpid.server.transport.ByteBufferSender;

import java.nio.ByteBuffer;

public class SimpleEncodeBufferSender implements ByteBufferSender {

    private final ByteBuffer byteBuffer;

    private final boolean isDirect;

    public SimpleEncodeBufferSender(int capacity, boolean isDirect) {
        this.isDirect = isDirect;
        this.byteBuffer = isDirect ? ByteBuffer.allocateDirect(capacity) : ByteBuffer.allocate(capacity);
    }

    @Override 
    public boolean isDirectBufferPreferred() {
        return isDirect;
    }

    @Override 
    public void send(QpidByteBuffer buffer) {
        byteBuffer.put(((SingleQpidByteBuffer) buffer.duplicate()).getUnderlyingBuffer());
    }

    @Override 
    public void flush() {}

    @Override 
    public void close() {}

    public ByteBuffer getBuffer() {
        return byteBuffer;
    }
}
