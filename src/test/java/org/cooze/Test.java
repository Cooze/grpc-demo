package org.cooze;

import org.cooze.rpc.client.AccountClient;
import org.cooze.rpc.client.BaseClient;
import org.cooze.rpc.client.GreeterClient;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/19
 */
public class Test {
    public static void main(String[] args) throws Exception {

        BaseClient client = new BaseClient("localhost", 50051);
        try {
            System.out.println("===============GreeterClient============");
            GreeterClient greeterClient = new GreeterClient(client);
            greeterClient.greet("Hello");

            System.out.println("===============AccountClient============");
            AccountClient accountClient = new AccountClient(client);

            System.out.println("===============新增============");
            accountClient.addAccount("张飞", "男", 45);

            System.out.println("===============查找============");
            accountClient.queryAccout("测试");


        } finally {
            client.shutdown();
        }
    }

}
