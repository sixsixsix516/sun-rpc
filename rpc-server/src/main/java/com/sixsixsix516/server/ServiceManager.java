package com.sixsixsix516.server;

import com.sixsixsix516.common.ReflectionUtil;
import com.sixsixsix516.proto.Request;
import com.sixsixsix516.proto.ServiceDescriptor;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务管理者
 *
 * @author sun 2020/2/14 18:55
 */
public class ServiceManager {

    private Map<ServiceDescriptor, ServiceInstance> service;

    public ServiceManager() {
        this.service = new ConcurrentHashMap<ServiceDescriptor, ServiceInstance>();
    }

    /**
     * 注册服务
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        List<Method> publicMethods = ReflectionUtil.getPublicMethods(interfaceClass);
        for (int i = 0; i < publicMethods.size(); i++) {
            Method method = publicMethods.get(i);
            ServiceInstance serviceInstance = new ServiceInstance(bean,method);
            ServiceDescriptor serviceDescriptor = ServiceDescriptor.from(interfaceClass, method);
            service.put(serviceDescriptor,serviceInstance);
        }
    }

    /**
     * 查询服务
     */
    public ServiceInstance lookup(Request request){
        ServiceDescriptor serviceDescriptor = request.getServiceDescriptor();
        return service.get(serviceDescriptor);
    }
}
