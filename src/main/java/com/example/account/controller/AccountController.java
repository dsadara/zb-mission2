package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.dto.AccountDto;
import com.example.account.dto.CreateAccount;
import com.example.account.service.AccountService;
import com.example.account.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AccountController {
    // AccountService를 실행시키는(?) 컨트롤러
    private final AccountService accountService;

    // 레디스 락 테스트
    private final RedisTestService redisTestService;

    @PostMapping("/account")
    public CreateAccount.Response createAccount(
            @RequestBody @Valid CreateAccount.Request request
    ) {
        AccountDto accountDto = accountService.createAccount(
                request.getUserId(),
                request.getIntialBalance()
        );
        return CreateAccount.Response.from(accountDto);
    }

    @GetMapping("/get-lock")
    public String getLock() {
        return redisTestService.getLock();
    }


    @GetMapping("/account/{id}")
    public Account getAccount(
            @PathVariable Long id
    ) {
        return accountService.getAccount(id);
    }
}
