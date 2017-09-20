package org.cooze.rpc.service.greeter;

import io.grpc.stub.StreamObserver;
import org.cooze.grpc.entity.HelloReply;
import org.cooze.grpc.entity.HelloRequest;
import org.cooze.grpc.service.GreeterGrpc;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/19
 */
public class GreeterImpl extends GreeterGrpc.GreeterImplBase {

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();

        //处理接收到的消息
        String msg = reply.getMessage();
        System.out.println("服务端收到消息:" + msg);

        //响应消息
        HelloReply response = reply.toBuilder().setMessage("世界你好！").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
