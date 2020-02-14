package com.sixsixsix516.client;

import com.sixsixsix516.common.ReflectionUtil;
import com.sixsixsix516.proto.Peer;
import com.sixsixsix516.transport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author sun 2020/2/14 21:13
 */
public class RandomTransportSelector implements TransportSelector {

    /**
     * 已经连接好的client
     */
    private List<TransportClient> transportClients;

    public RandomTransportSelector(){
        this.transportClients = new ArrayList<>();
    }

    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count,1);
        peers.forEach(peer ->{
            TransportClient transportClient = ReflectionUtil.newInstance(clazz);
            transportClient.connect(peer);
            transportClients.add(transportClient);
        });
    }

    @Override
    public synchronized TransportClient select() {
        int i = new Random().nextInt(transportClients.size());
        return transportClients.remove(i);
    }

    @Override
    public synchronized void release(TransportClient client) {
        transportClients.add(client);
    }

    @Override
    public synchronized void close() {
        transportClients.forEach(TransportClient::close);
        transportClients.clear();
    }
}
