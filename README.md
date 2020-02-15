#sun-rpc
基于Java8、Jetty、JDK代理实现的 rpc服务

---

### 1. 系统简易图
![系统简易图](/img/系统简易图.png)

<br>

**三个角色**
1. 服务端: 即服务提供者,服务端需要将服务注册到 服务管理器 中
2. 客户端: 服务调用者, 需要去服务
3. 服务管理者: 维护所有服务信息

<br>

### 2. 系统模块
![系统模块](/img/系统模块.png)
1. common: 放置通用工具、代码
2. proto: 定义请求、响应、服务信息等结构
3. codec: 序列化, 提供对网络传输的系列化支持
4. transport: 提供网络连接、请求发送
5. server: 提供服务注册功能、查找、启动等
6. client: 提供服务调用、链接选择等

<br>

### 3.使用方式
在rpc-example包中可见完整可运行代码示例

**server**

```java
public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        // 注册服务
        rpcServer.register(CalcService.class,new CalcServiceImpl());
        rpcServer.start();
    }

}
```

**client**
```java
public class Client {
    public static void main(String[] args) {
        RpcClient rpcClient = new RpcClient();
        CalcService service = rpcClient.getProxy(CalcService.class);
        int add = service.add(1, 2);
        int minus = service.minus(1, 2);
        System.out.println("加法 : " + add);
        System.out.println("减法 : " + minus);
    }
}
```


 






