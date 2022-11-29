package com.example.account.repository;

import com.example.account.domain.Account;
import com.example.account.domain.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    // Account 테이블에 접속하기 위한 JPA에서 제공하는 인터페이스
    Optional<Account> findFirstByOrderByIdDesc(); // Id 내림차순 첫번째 원소 가져오기

    // 해당 accountUser가 소유하고 있는 계좌의 개수 반환
    Integer countByAccountUser(AccountUser accountuser);
}
