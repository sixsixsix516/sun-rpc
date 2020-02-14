package com.sixsixsix516.server;

import com.sixsixsix516.codec.Decoder;
import com.sixsixsix516.codec.Encoder;
import com.sixsixsix516.common.ReflectionUtil;
import com.sixsixsix516.proto.Request;
import com.sixsixsix516.proto.Response;
import com.sixsixsix516.transport.RequestHandler;
import com.sixsixsix516.transport.TransportServer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.sql.Ref;

/**
 * @author sun 2020/2/14 20:50
 */
@Data
public class RpcServer {

    private RpcServerConfig rpcServerConfig;
    private TransportServer transportServer;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer(){
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
        this.transportServer = ReflectionUtil.newInstance(rpcServerConfig.getTransportClass());
        this.encoder = ReflectionUtil.newInstance(rpcServerConfig.getEncoderClass());
        this.decoder = ReflectionUtil.newInstance(rpcServerConfig.getDecoderClass());
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

        this.transportServer.init(rpcServerConfig.getPort(), (recive, out) -> {
            Response response = new Response();
            try {
                byte[] inBytes = IOUtils.readFully(recive, recive.available());
                Request request = decoder.decode(inBytes, Request.class);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object res = serviceInvoker.invoke(serviceInstance, request);
                response.setData(res);
            } catch (Exception e) {
                e.printStackTrace();
                response.setCode(-1);
                response.setMessage(e.getMessage());
            }finally {
                byte[] encode = encoder.encode(response);
                try {
                    out.write(encode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.transportServer.start();
    }

    public void stop() {
        this.transportServer.stop();
    }
}
