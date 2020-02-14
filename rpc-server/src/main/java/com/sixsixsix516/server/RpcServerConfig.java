package com.sixsixsix516.server;

import com.sixsixsix516.codec.Decoder;
import com.sixsixsix516.codec.Encoder;
import com.sixsixsix516.codec.JSONDecoder;
import com.sixsixsix516.codec.JSONEncoder;
import com.sixsixsix516.transport.HTTPTransportServer;
import com.sixsixsix516.transport.TransportClient;
import com.sixsixsix516.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 * @author sun 2020/2/14 18:49
 */
@Data
public class RpcServerConfig {

    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;

}
