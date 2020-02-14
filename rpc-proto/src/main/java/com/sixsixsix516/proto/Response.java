package com.sixsixsix516.proto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表示一个响应
 *
 * @author sun 2020/2/14 16:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    /**
     * 服务状态码 0成功 非0失败
     */
    private int code ;

    /**
     * 具体错误信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;


    public String getMessage(){
        if(message == null || message.equals("")){
            return "ok";
        }
        return message;
    }
}
