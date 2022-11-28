package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountStatus;
import com.example.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor    // 생성자로 의존성 주입을 하기 위한 롬복
public class AccountService {
    // AccountRepository를 사용하는 서비스

    // @Autowired를 쓰면 AccountService 테스트시
    // AccountRepository 의존성을 담아주기 어렵다
    // 여기선 @RequiredArgsConstructor가 알아서 AccountRepository 의존성 주입을 함
    private final AccountRepository accountRepository;

    @Transactional
    public void createAccount() {
        // Account 테이블에 데이터를 저장하는 메소드

        // Account 생성 (id는 자동생성됨)
        Account account = Account.builder()
                .accountNumber("40000")
                .accountStatus(AccountStatus.IN_USE)
                .build();

        // accountRepository에 저장
        accountRepository.save(account);

    }

    @Transactional
    public Account getAccount(Long id) {
        if (id < 0) {
            throw new RuntimeException("Minus");
        }

        // optional을 반환해서 .get()을 붙여줌
        return accountRepository.findById(id).get();
    }
}
