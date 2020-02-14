package com.sixsixsix516.client;

import com.sixsixsix516.codec.Decoder;
import com.sixsixsix516.codec.Encoder;
import com.sixsixsix516.codec.JSONDecoder;
import com.sixsixsix516.codec.JSONEncoder;
import com.sixsixsix516.proto.Peer;
import com.sixsixsix516.transport.HTTPTransportClient;
import com.sixsixsix516.transport.TransportClient;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sun 2020/2/14 21:20
 */
@Data
public class RpcClientConfig {

    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;

    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;

    private Class<? extends TransportSelector> selecorClass = RandomTransportSelector.class;

    private int connectCount = 1;
    private List<Peer>  servers = new ArrayList<Peer>(){{
       add(new Peer("127.0.0.1",3000));
    }};


}
