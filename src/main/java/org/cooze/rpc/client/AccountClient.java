package org.cooze.rpc.client;

import com.sun.javafx.binding.StringFormatter;
import org.cooze.grpc.entity.Account;
import org.cooze.grpc.entity.AccountResponse;
import org.cooze.grpc.service.AccountGrpc;

import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc
 * @date 2017/9/19
 */
public class AccountClient {

    private final AccountGrpc.AccountBlockingStub accountBlockingStub;

    private final BaseClient client;

    public AccountClient(BaseClient client) {
        this.client = client;
        this.accountBlockingStub = client.getAccountBlockingStub();
    }

    public void addAccount(String name, String sex, int age) {

        Account account = Account.getDefaultInstance().toBuilder()
                .setName(name)
                .setSex(sex)
                .setAge(age)
                .build();

        AccountResponse response = this.accountBlockingStub.addAccount(account);

        System.out.println(StringFormatter.format("返回消息:%s\n状态:%d", response.getMsg(), response.getCode()).get());
    }


    public void queryAccout(String name) {
        Account account = Account.getDefaultInstance().toBuilder()
                .setName(name).build();
        AccountResponse response = this.accountBlockingStub.getAccoutByName(account);

        System.out.println(StringFormatter.format("返回消息:%s\n状态:%d", response.getMsg(), response.getCode()).getValue());
        System.out.println("查询结果：");
        List<Account> list = response.getResultsList();
        for (Account acc : list) {
            System.out.println(StringFormatter.format("姓名:%s,性别:%s,年龄:%d", acc.getName(), acc.getSex(), acc.getAge()).get());
        }

    }


}
