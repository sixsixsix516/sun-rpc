package com.sixsixsix516.client;

import com.sixsixsix516.codec.Decoder;
import com.sixsixsix516.codec.Encoder;
import com.sixsixsix516.common.ReflectionUtil;

import java.lang.reflect.Proxy;
import java.sql.Ref;

/**
 * @author sun 2020/2/14 21:25
 */
public class RpcClient {

    private RpcClientConfig rpcClientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;


    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;
        this.encoder = ReflectionUtil.newInstance(rpcClientConfig.getEncoderClass());
        this.decoder = ReflectionUtil.newInstance(rpcClientConfig.getDecoderClass());
        this.transportSelector = ReflectionUtil.newInstance(rpcClientConfig.getSelecorClass());

        this.transportSelector.init(
                this.rpcClientConfig.getServers(),
                this.rpcClientConfig.getConnectCount(),
                this.rpcClientConfig.getTransportClass()
        );

    }

    public <T> T getProxy(Class <T>clazz){
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,transportSelector)
        );
    }


}
