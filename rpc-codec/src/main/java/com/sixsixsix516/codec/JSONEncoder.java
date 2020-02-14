package com.sixsixsix516.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化
 *
 * @author sun 2020/2/14 18:15
 */
public class JSONEncoder implements Encoder {

    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);
    }
}
