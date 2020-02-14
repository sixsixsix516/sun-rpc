package com.sixsixsix516.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示网络传输的一个端点
 *
 * @author sun 2020/2/14 16:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peer {

    private String host;
    private int port;
}
