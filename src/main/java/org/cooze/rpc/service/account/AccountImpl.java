package org.cooze.rpc.service.account;

import com.sun.javafx.binding.StringFormatter;
import io.grpc.stub.StreamObserver;
import org.cooze.grpc.entity.Account;
import org.cooze.grpc.entity.AccountResponse;
import org.cooze.grpc.service.AccountGrpc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cooze
 * @version 1.0.0
 * @desc account RPC 实现类
 * @date 2017/9/19
 */
public class AccountImpl extends AccountGrpc.AccountImplBase {

    @Override
    public void addAccount(Account request, StreamObserver<AccountResponse> responseObserver) {
        //处理请求参数
        System.out.println(StringFormatter.format("新增用户:%s\n性别:%s\n年龄:%d岁", request.getName(), request.getSex(), request.getAge()).get());

        //处理响应参数
        AccountResponse response = AccountResponse.getDefaultInstance().toBuilder()
                .setCode(10000)
                .setMsg("success!").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getAccoutByName(Account request, StreamObserver<AccountResponse> responseObserver) {
        //处理请求参数
        System.out.println(StringFormatter.format("请求查询用户名:%s", request.getName()).get());

        //处理响应参数
        List<Account> list = new ArrayList<>();
        Account account1 = Account.getDefaultInstance().toBuilder()
                .setName("张三")
                .setAge(20)
                .setSex("男").build();
        list.add(account1);

        Account account2 = Account.getDefaultInstance().toBuilder()
                .setAge(30)
                .setSex("男")
                .setName("李四").build();

        list.add(account2);

        AccountResponse response = AccountResponse.getDefaultInstance().toBuilder()
                .setCode(10000)
                .setMsg("success!")
                .addAllResults(list)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
