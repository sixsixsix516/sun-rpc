package com.sixsixsix516.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示一个请求
 *
 * @author sun 2020/2/14 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    private ServiceDescriptor serviceDescriptor;
    // 请求参数
    private Object[] parameters;

}
