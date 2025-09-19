package org.apache.qpid.server.bytebuffer;

import io.netty.channel.ChannelHandlerContext;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public interface QpidByteBuffer extends AutoCloseable
{
    static QpidByteBuffer allocate(boolean direct, int size)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.allocate(direct, size);
    }

    static QpidByteBuffer allocate(int size)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.allocate(size);
    }

    static QpidByteBuffer allocateDirect(int size)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.allocateDirect(size);
    }

    static QpidByteBuffer asQpidByteBuffer(InputStream stream) throws IOException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.asQpidByteBuffer(stream);
    }

    static SSLEngineResult encryptSSL(SSLEngine engine,
                                      Collection<QpidByteBuffer> buffers,
                                      QpidByteBuffer dest) throws SSLException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.encryptSSL(engine, buffers, dest);
    }


    static SSLEngineResult decryptSSL(SSLEngine engine, QpidByteBuffer src, QpidByteBuffer dst) throws SSLException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.decryptSSL(engine, src, dst);
    }

    static QpidByteBuffer inflate(QpidByteBuffer compressedBuffer) throws IOException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.inflate(compressedBuffer);
    }

    static QpidByteBuffer deflate(QpidByteBuffer uncompressedBuffer) throws IOException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.deflate(uncompressedBuffer);
    }

    static long write(GatheringByteChannel channel, Collection<QpidByteBuffer> qpidByteBuffers)
            throws IOException
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.write(channel, qpidByteBuffers);
    }

    static long write(ChannelHandlerContext channelHandlerContext, Collection<QpidByteBuffer> qpidByteBuffers)
            throws IOException {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.write(channelHandlerContext, qpidByteBuffers);
    }

    static long write(ChannelHandlerContext channelHandlerContext, QpidByteBuffer qpidByteBuffer) throws IOException {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.write(channelHandlerContext, qpidByteBuffer);
    }

    static QpidByteBuffer wrap(ByteBuffer wrap)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.wrap(wrap);
    }

    static QpidByteBuffer wrap(byte[] data)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.wrap(data);
    }

    static QpidByteBuffer wrap(byte[] data, int offset, int length)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.wrap(data, offset, length);
    }

    static void initialisePool(int bufferSize, int maxPoolSize, double sparsityFraction)
    {
        org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.initialisePool(bufferSize, maxPoolSize, sparsityFraction);
    }

    /**
     * Test use only
     */
    static void deinitialisePool()
    {
        org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.deinitialisePool();
    }

    static void returnToPool(ByteBuffer buffer)
    {
        org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.returnToPool(buffer);
    }

    static int getPooledBufferSize()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.getPooledBufferSize();
    }

    static long getAllocatedDirectMemorySize()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.getAllocatedDirectMemorySize();
    }

    static int getNumberOfBuffersInUse()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.getNumberOfBuffersInUse();
    }

    static int getNumberOfBuffersInPool()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.getNumberOfBuffersInPool();
    }

    static long getPooledBufferDisposalCounter()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.getPooledBufferDisposalCounter();
    }

    static QpidByteBuffer reallocateIfNecessary(QpidByteBuffer data)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.reallocateIfNecessary(data);
    }

    static QpidByteBuffer concatenate(List<QpidByteBuffer> buffers)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.concatenate(buffers);
    }

    static QpidByteBuffer concatenate(QpidByteBuffer... buffers)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.concatenate(buffers);
    }

    static QpidByteBuffer emptyQpidByteBuffer()
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.emptyQpidByteBuffer();
    }

    static ThreadFactory createQpidByteBufferTrackingThreadFactory(ThreadFactory factory)
    {
        return org.apache.qpid.server.bytebuffer.QpidByteBufferFactory.createQpidByteBufferTrackingThreadFactory(factory);
    }

    @Override
    void close();

    QpidByteBuffer put(int index, byte b);

    QpidByteBuffer putShort(int index, short value);

    QpidByteBuffer putChar(int index, char value);

    QpidByteBuffer putInt(int index, int value);

    QpidByteBuffer putLong(int index, long value);

    QpidByteBuffer putFloat(int index, float value);

    QpidByteBuffer putDouble(int index, double value);

    QpidByteBuffer put(byte b);

    QpidByteBuffer putUnsignedByte(short s);

    QpidByteBuffer putShort(short value);

    QpidByteBuffer putUnsignedShort(int i);

    QpidByteBuffer putChar(char value);

    QpidByteBuffer putInt(int value);

    QpidByteBuffer putUnsignedInt(long value);

    QpidByteBuffer putLong(long value);

    QpidByteBuffer putFloat(float value);

    QpidByteBuffer putDouble(double value);

    QpidByteBuffer put(byte[] src);

    QpidByteBuffer put(byte[] src, int offset, int length);

    QpidByteBuffer put(ByteBuffer src);

    QpidByteBuffer put(QpidByteBuffer src);

    byte get(int index);

    short getShort(int index);

    int getUnsignedShort(int index);

    char getChar(int index);

    int getInt(int index);

    long getLong(int index);

    float getFloat(int index);

    double getDouble(int index);

    byte get();

    short getUnsignedByte();

    short getShort();

    int getUnsignedShort();

    char getChar();

    int getInt();

    long getUnsignedInt();

    long getLong();

    float getFloat();

    double getDouble();

    QpidByteBuffer get(byte[] dst);

    QpidByteBuffer get(byte[] dst, int offset, int length);

    void copyTo(byte[] dst);

    void copyTo(ByteBuffer dst);

    void putCopyOf(QpidByteBuffer source);

    boolean isDirect();

    void dispose();

    InputStream asInputStream();

    long read(ScatteringByteChannel channel) throws IOException;

    QpidByteBuffer reset();

    QpidByteBuffer rewind();

    boolean hasArray();

    byte[] array();

    QpidByteBuffer clear();

    QpidByteBuffer compact();

    int position();

    QpidByteBuffer position(int newPosition);

    int limit();

    QpidByteBuffer limit(int newLimit);

    QpidByteBuffer mark();

    int remaining();

    boolean hasRemaining();

    boolean hasRemaining(int atLeast);

    QpidByteBuffer flip();

    int capacity();

    QpidByteBuffer duplicate();

    QpidByteBuffer slice();

    QpidByteBuffer view(int offset, int length);

    boolean isSparse();
}
