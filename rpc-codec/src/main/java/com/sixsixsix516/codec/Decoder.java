package com.sixsixsix516.codec;

/**
 * 反序列化接口
 */
public interface Decoder {

    <T> T  decode(byte[] bytes,Class<T> clazz);
}
