package com.example.account;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AccountDtoTest {
    @Test
    void accountDto() {
        //given
        //when
        //then

        AccountDto accountDto = new AccountDto("accountNumber", "summver", LocalDateTime.now());

        System.out.println(accountDto.toString());
    }

}