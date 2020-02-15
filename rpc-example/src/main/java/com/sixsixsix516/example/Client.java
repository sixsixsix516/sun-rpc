package com.sixsixsix516.example;

import com.sixsixsix516.client.RpcClient;

/**
 * @author sun 2020/2/14 21:46
 */
public class Client {

    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService service = rpcClient.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int minus = service.minus(1, 2);
        System.out.println("加法 : " + add);
        System.out.println("减法 : " + minus);
    }
}
