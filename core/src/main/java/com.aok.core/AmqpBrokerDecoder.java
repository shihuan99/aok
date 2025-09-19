package com.aok.core;

import org.apache.qpid.server.bytebuffer.QpidByteBuffer;
import org.apache.qpid.server.protocol.v0_8.AMQFrameDecodingException;
import org.apache.qpid.server.protocol.v0_8.ServerDecoder;
import org.apache.qpid.server.protocol.v0_8.transport.AMQProtocolVersionException;
import org.apache.qpid.server.protocol.v0_8.transport.ServerChannelMethodProcessor;
import org.apache.qpid.server.protocol.v0_8.transport.ServerMethodProcessor;

import java.io.IOException;


public class AmqpBrokerDecoder extends ServerDecoder {

    private static final int bufferSize = 4 * 1024;
    private volatile QpidByteBuffer netInputBuffer;

    public AmqpBrokerDecoder(ServerMethodProcessor<? extends ServerChannelMethodProcessor> methodProcessor) {
        super(methodProcessor);
        netInputBuffer = QpidByteBuffer.allocateDirect(bufferSize);
    }

    @Override
    public ServerMethodProcessor<? extends ServerChannelMethodProcessor> getMethodProcessor() {
        return super.getMethodProcessor();
    }

    @Override
    public void decodeBuffer(QpidByteBuffer buf) throws AMQFrameDecodingException, AMQProtocolVersionException,
            IOException {
        int messageSize = buf.remaining();
        if (netInputBuffer.remaining() < messageSize) {
            QpidByteBuffer oldBuffer = netInputBuffer;
            if (oldBuffer.position() != 0) {
                oldBuffer.limit(oldBuffer.position());
                oldBuffer.slice();
                oldBuffer.flip();
                netInputBuffer = QpidByteBuffer.allocateDirect(bufferSize + oldBuffer.remaining() + messageSize);
                netInputBuffer.put(oldBuffer);
            } else {
                netInputBuffer = QpidByteBuffer.allocateDirect(bufferSize + messageSize);
            }
        }
        netInputBuffer.put(buf);
        netInputBuffer.flip();
        super.decodeBuffer(netInputBuffer);
        restoreApplicationBufferForWrite();
    }

    protected void restoreApplicationBufferForWrite() {
        try (QpidByteBuffer oldNetInputBuffer = netInputBuffer) {
            int unprocessedDataLength = netInputBuffer.remaining();
            netInputBuffer.limit(netInputBuffer.capacity());
            netInputBuffer = oldNetInputBuffer.slice();
            netInputBuffer.limit(unprocessedDataLength);
        }
        if (netInputBuffer.limit() != netInputBuffer.capacity()) {
            netInputBuffer.position(netInputBuffer.limit());
            netInputBuffer.limit(netInputBuffer.capacity());
        } else {
            try (QpidByteBuffer currentBuffer = netInputBuffer) {
                int newBufSize;
                if (currentBuffer.capacity() < bufferSize) {
                    newBufSize = bufferSize;
                } else {
                    newBufSize = currentBuffer.capacity() + bufferSize;
                }
                netInputBuffer = QpidByteBuffer.allocateDirect(newBufSize);
                netInputBuffer.put(currentBuffer);
            }
        }
    }

    public void close() {
        netInputBuffer = null;
    }
}
