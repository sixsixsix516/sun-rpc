package com.sixsixsix516.transport;

import com.sixsixsix516.proto.Peer;
import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author sun 2020/2/14 18:28
 */
public class HTTPTransportClient implements TransportClient {

    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":" + peer.getPort();

    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn = (HttpURLConnection) new URL(url).openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            httpConn.connect();
            IOUtils.copy(data, httpConn.getOutputStream());

            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return httpConn.getInputStream();
            }else{
                return httpConn.getErrorStream();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {

    }
}
