package com.sixsixsix516.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表示一个服务
 *
 * @author sun 2020/2/14 16:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {

    private String clazz;

    private String method;

    /**
     * 返回值类型
     */
    private String returnType;

    /**
     * 参数列表
     */
    private List<String> parameterTypes;


    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor();
        serviceDescriptor.setClazz(clazz.getName());
        serviceDescriptor.setMethod(method.getName());
        serviceDescriptor.setReturnType(method.getReturnType().getName());
        serviceDescriptor.setParameterTypes(Arrays.stream(method.getParameterTypes()).map(Class::getName).collect(Collectors.toList()));

        return serviceDescriptor;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServiceDescriptor serviceDescriptor = (ServiceDescriptor) obj;
        return this.toString().equals(serviceDescriptor.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
