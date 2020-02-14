package com.sixsixsix516.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 网络请求处理器
 *
 * @author sun 2020/2/14 18:26
 */
@FunctionalInterface
public interface RequestHandler {

    void onRequest(InputStream recive, OutputStream out);
}
