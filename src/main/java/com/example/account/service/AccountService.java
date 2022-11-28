package com.example.account.service;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import com.example.account.dto.AccountDto;
import com.example.account.exception.AccountException;
import com.example.account.repository.AccountRepository;
import com.example.account.repository.AccountUserRepository;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;

import static com.example.account.type.AccountStatus.*;

@Service
@RequiredArgsConstructor    // 생성자로 의존성 주입을 하기 위한 롬복
public class AccountService {
    // AccountRepository를 사용하는 서비스

    // @Autowired를 쓰면 AccountService 테스트시
    // AccountRepository 의존성을 담아주기 어렵다
    // 여기선 @RequiredArgsConstructor가 알아서 AccountRepository 의존성 주입을 함
    private final AccountRepository accountRepository;
    private final AccountUserRepository accountUserRepository;

    /**
     * 사용자가 존재하는지 조회
     * 계좌의 번호를 생성
     * 계좌를 저장하고, 그 정보를 넘김
     */
    @Transactional
    public AccountDto createAccount(Long userId, Long initialBalance) {
        // 해당 userId가 없으면 런타임 에러를 던져줌
        AccountUser accountUser = accountUserRepository.findById(userId)
                .orElseThrow(() -> new AccountException(ErrorCode.USER_NOT_FOUND));

        // 가장 최근에 생성된 계좌정보를 가져옴, Optional을 사용하여 간결하게 함
        String newAccountNumber = accountRepository.findFirstByOrderByIdDesc()
                // 계좌가 있으면 계좌번호를 가져온 후 숫자로 파싱 그리고 1을 더함 그리고 다시 문자열로 변경
                .map(account -> (Integer.parseInt(account.getAccountNumber())) + 1 + "")
                // 계좌가 없으면 아래 숫자로 계좌번호 설정
                .orElse("1000000000");

        // 계좌 생성
        return AccountDto.fromEntity(
                // Account 반환
                accountRepository.save(Account.builder()
                                .accountUser(accountUser)
                                .accountStatus(IN_USE) // AccountStatus static import함
                                .accountNumber(newAccountNumber)
                                .balance(initialBalance)
                                .registeredAt(LocalDateTime.now())
                                .build())
        );
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
