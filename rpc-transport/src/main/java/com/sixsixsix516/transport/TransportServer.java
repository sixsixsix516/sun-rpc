package com.sixsixsix516.transport;

/**
 * @author sun 2020/2/14 18:25
 */
public interface TransportServer {

    void start();

    void stop();

    void init(int port, RequestHandler requestHandler);
}
