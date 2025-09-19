/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aok.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.apache.qpid.server.bytebuffer.QpidByteBuffer;
import org.apache.qpid.server.protocol.ProtocolVersion;
import org.apache.qpid.server.protocol.v0_8.AMQFrameDecodingException;
import org.apache.qpid.server.protocol.v0_8.AMQShortString;
import org.apache.qpid.server.protocol.v0_8.FieldTable;
import org.apache.qpid.server.protocol.v0_8.transport.ProtocolInitiation;
import org.apache.qpid.server.protocol.v0_8.transport.ServerChannelMethodProcessor;
import org.apache.qpid.server.protocol.v0_8.transport.ServerMethodProcessor;

import java.io.IOException;

public class AmqpConnection extends AmqpCommandDecoder implements ServerMethodProcessor<ServerChannelMethodProcessor> {

    protected AmqpBrokerDecoder brokerDecoder;
    
    @Override
    public void receiveConnectionStartOk(FieldTable clientProperties, AMQShortString mechanism, byte[] response, AMQShortString locale) {
        
    }

    @Override
    public void receiveConnectionSecureOk(byte[] response) {

    }

    @Override
    public void receiveConnectionTuneOk(int channelMax, long frameMax, int heartbeat) {

    }

    @Override
    public void receiveConnectionOpen(AMQShortString virtualHost, AMQShortString capabilities, boolean insist) {

    }

    @Override
    public void receiveChannelOpen(int channelId) {

    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return null;
    }

    @Override
    public ServerChannelMethodProcessor getChannelMethodProcessor(int channelId) {
        return null;
    }

    @Override
    public void receiveConnectionClose(int replyCode, AMQShortString replyText, int classId, int methodId) {

    }

    @Override
    public void receiveConnectionCloseOk() {

    }

    @Override
    public void receiveHeartbeat() {

    }

    @Override
    public void receiveProtocolHeader(ProtocolInitiation protocolInitiation) {

    }

    @Override
    public void setCurrentMethod(int classId, int methodId) {

    }

    @Override
    public boolean ignoreAllButCloseOk() {
        return false;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.brokerDecoder = new AmqpBrokerDecoder(this);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws AMQFrameDecodingException, IOException {
        ByteBuf buffer = (ByteBuf) msg;
        brokerDecoder.decodeBuffer(QpidByteBuffer.wrap(buffer.nioBuffer()));
    }
}
