package com.sixsixsix516.client;

import com.sixsixsix516.codec.Decoder;
import com.sixsixsix516.codec.Encoder;
import com.sixsixsix516.proto.Request;
import com.sixsixsix516.proto.Response;
import com.sixsixsix516.proto.ServiceDescriptor;
import com.sixsixsix516.transport.TransportClient;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Queue;

/**
 * 调用远程服务代理类
 *
 * @author sun 2020/2/14 21:30
 */
public class RemoteInvoker implements InvocationHandler {

    private RpcClientConfig rpcClientConfig;
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;


    public RemoteInvoker(Class clazz,
                         Encoder encoder, Decoder decoder,
                         TransportSelector transportSelector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response res = invokeRemote(request);

        if (res == null || res.getCode() != 0) {
            //失败的情况
            throw new IllegalStateException("调用远程服务失败: " + res.getMessage());
        }

        return res.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient transportClient = null;
        Response response = null;
        try {
            transportClient  = transportSelector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = transportClient.write(new ByteArrayInputStream(outBytes));

            byte[] inBytes = IOUtils.readFully(revice, revice.available());
            response = decoder.decode(inBytes, Response.class);


        } catch (Exception e) {
            e.printStackTrace();
            response = new Response();
            response.setCode(1);
            response.setMessage(e.getMessage());

        } finally {
            if (transportClient != null) {
                transportSelector.release(transportClient);
            }
        }

        return response;

    }
}
