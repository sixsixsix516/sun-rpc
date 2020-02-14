package com.sixsixsix516.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 表示一个具体服务
 *
 * @author sun 2020/2/14 18:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceInstance {

    private Object target;

    private Method method;

}
