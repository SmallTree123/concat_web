package com.nylgsc.service;

import com.nylgsc.entity.Account;
import com.nylgsc.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService  {

    @Autowired
    AccountMapper accountMapper;

    /**
     * 从用户账户中借出
     */
    @Override
    public void debit(String userId, int money) {
        System.out.println("成功借出。。。钱");
        Account account = new Account(1L,new BigDecimal(12));
        accountMapper.insert(account);
    }
}
