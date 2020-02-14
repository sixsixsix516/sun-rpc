package com.sixsixsix516.transport;

import com.sixsixsix516.proto.Peer;

import java.io.InputStream;

/**
 * @author sun 2020/2/14 18:23
 */
public interface TransportClient {

    /**
     * 创建链接
     */
    void connect(Peer peer);

    /**
     * 发送数据
     */
    InputStream write(InputStream data);

    /**
     * 关闭连接
     */
    void close();

}
