package com.example.account.service;

import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LockService {
    // 레디스 서비스를 서보는 클래스

    // 변수 이름이 RedisRepositoryConfig의
    // redissonClient 빈 이름과 같으므로 빈이 자동으로 주입 됨
    private final RedissonClient redissonClient;

    public void lock(String accountNumber) {
        // 자물쇠를 받음
        RLock lock = redissonClient.getLock(getLockKey(accountNumber));
        log.debug("Trying lock for accountNumber : {}", accountNumber);

        try {
            // 1초 동안 lock이 있는지 기다리고 (스핀 락)
            // 3초 동안 lock을 갖고 있다가 풀어주는 거임
            boolean isLock =
                    lock.tryLock(1, 15, TimeUnit.SECONDS);
            if (!isLock) {
                log.error("===========Lock acquisition failed======");
                throw new AccountException(ErrorCode.ACCOUNT_TRANSACTION_LOCK);
            }
        } catch (AccountException e) {
            throw e;
        } catch (Exception e) {
            log.error("Redis lock failed", e);
        }
    }

    public void unlock(String accountNumber) {
        log.debug("Unlock for accountNumber : {} ", accountNumber);
        redissonClient.getLock(getLockKey(accountNumber)).unlock();
    }

    private static String getLockKey(String accountNumber) {
        return "ACLK:" + accountNumber;
    }
}
