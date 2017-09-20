package org.cooze.rpc.client;

import io.grpc.StatusRuntimeException;
import org.cooze.grpc.entity.HelloReply;
import org.cooze.grpc.entity.HelloRequest;
import org.cooze.grpc.service.GreeterGrpc;


/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/18
 */
public class GreeterClient {

    private final GreeterGrpc.GreeterBlockingStub blockingStub;


    public GreeterClient(BaseClient client) {
        blockingStub = client.getGreeterBlockingStub();
    }

    public void greet(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
            String msg = response.getMessage();
            //接收到服务端返回的消息
            System.out.println("客户端收到消息:" + msg);
        } catch (StatusRuntimeException e) {
            return;
        }
    }
}
