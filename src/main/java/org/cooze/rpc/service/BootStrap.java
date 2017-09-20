package org.cooze.rpc.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.cooze.rpc.service.account.AccountImpl;
import org.cooze.rpc.service.greeter.GreeterImpl;

import java.io.IOException;

/**
 * @author cooze
 * @version 1.0.0
 * @desc RPC启动引导类
 * @date 2017/9/19
 */
public class BootStrap {

    private Server server;

    /**
     * 服务启动类
     *
     * @param port 端口
     * @throws IOException
     */
    private void start(int port) throws IOException {
        server = ServerBuilder.forPort(port)
                //注册服务
                .addService(new GreeterImpl())
                .addService(new AccountImpl())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** JVM 关闭,导致gRPC服务关闭!");
                BootStrap.this.stop();
                System.err.println("*** 服务关闭");
            }
        });
    }

    /**
     * RPC 服务关闭
     */
    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * 设置守护进程
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * RPC服务启动main函数
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final BootStrap server = new BootStrap();
        server.start(50051);
        server.blockUntilShutdown();
    }

}
