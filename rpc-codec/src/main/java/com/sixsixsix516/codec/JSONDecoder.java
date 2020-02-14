package com.sixsixsix516.codec;

import com.alibaba.fastjson.JSON;

/**
 * JSON的反序列化
 *
 * @author sun 2020/2/14 18:17
 */
public class JSONDecoder implements Decoder{


    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
