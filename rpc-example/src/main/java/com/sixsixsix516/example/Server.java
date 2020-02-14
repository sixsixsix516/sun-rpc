package com.sixsixsix516.example;

import com.sixsixsix516.server.RpcServer;

/**
 * @author sun 2020/2/14 21:47
 */
public class Server {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class,new CalcServiceImpl());
        rpcServer.start();

    }

}
