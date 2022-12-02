package com.example.account.service;

import com.example.account.dto.UseBalance;
import com.example.account.exception.AccountException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.account.type.ErrorCode.ACCOUNT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LockAopAspectTest {
    @Mock
    private LockService lockService;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private LockAopAspect lockAopAspect;

    @Test
    void lockAndUnlock_evenIfThrow() throws Throwable {
        //given
        // lock에 계좌번호가 잘 들어갔는지 argument를 확인해야 함
        ArgumentCaptor<String> lockArgumentCaptor =
                ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unlockArgumentCaptor =
                ArgumentCaptor.forClass(String.class);
        UseBalance.Request request =
                new UseBalance.Request(123L, "54321", 1000L);
        given(proceedingJoinPoint.proceed())
                .willThrow(new AccountException(ACCOUNT_NOT_FOUND));

        //when
        assertThrows(AccountException.class, () ->
                lockAopAspect.aroundMethod(proceedingJoinPoint, request));

        //then
        verify(lockService, times(1))
                .lock(lockArgumentCaptor.capture());
        verify(lockService, times(1))
                .unlock(unlockArgumentCaptor.capture());
        assertEquals("54321", lockArgumentCaptor.getValue());
        assertEquals("54321", unlockArgumentCaptor.getValue());
    }
}