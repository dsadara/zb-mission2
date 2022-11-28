package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.type.AccountStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// SpringBootTest가 AccountService 내부의 의존성을 빈으로 테스트 컨테이너에 띄어 놓음
@SpringBootTest
class AccountServiceTest {

    // Autowired로 accountService 내의 의존성을 다 주입해줌
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void init() {
        // 테스트 전에 무조건 동작시키는 것
        accountService.createAccount(1L, 100L);
    }

    @Test
    @DisplayName("Test 이름 변경")
    void testGetAccount() {
        // 순차 생성이므로 1번 아이디로 해서 가져오면 됨
        Account account = accountService.getAccount(2L);

        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());
    }

    @Test
    void testGetAccount2() {
        Account account = accountService.getAccount(2L);

        assertEquals("40000", account.getAccountNumber());
        assertEquals(AccountStatus.IN_USE, account.getAccountStatus());
    }

}