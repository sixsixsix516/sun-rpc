package com.sixsixsix516.server;

import com.sixsixsix516.common.ReflectionUtil;
import com.sixsixsix516.proto.Request;

/**
 * 调用具体服务
 *
 * @author sun 2020/2/14 20:49
 */
public class ServiceInvoker {

    public Object invoke(ServiceInstance service, Request request) {
        return ReflectionUtil.invoke(service.getTarget(),
                service.getMethod(),
                request.getParameters());
    }
}
