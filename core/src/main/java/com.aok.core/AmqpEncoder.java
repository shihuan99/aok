package com.aok.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.qpid.server.protocol.v0_8.transport.AMQDataBlock;

import java.nio.ByteBuffer;

@Slf4j
public class AmqpEncoder extends MessageToByteEncoder<AMQDataBlock> {

    @Override
    public void encode(ChannelHandlerContext ctx, AMQDataBlock frame, ByteBuf out) {
        try {
            SimpleEncodeBufferSender sender = new SimpleEncodeBufferSender((int) frame.getSize(), true);
            frame.writePayload(sender);
            ByteBuffer data = (ByteBuffer) sender.getBuffer().flip();
            out.writeBytes(data);
        } catch (Exception e) {
            log.error("channel {} encode exception {}", ctx.channel().toString(), e);
            ctx.close();
        }
    }
}