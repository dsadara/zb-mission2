package com.example.account.domain;

import lombok.*;

import javax.persistence.*;

// Entity나 dto에 기본적으로 넣어주는 롬복 어노테이션이라고 함
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Entitiy는 일종의 테이블 설정 클래스
@Entity
public class Account {
    @Id // id를 pk로 지정
    @GeneratedValue // pk 자동 생성 기능
    private Long id;

    private String accountNumber;

    @Enumerated(EnumType.STRING)  // Enum값을 정수가 아닌 실제 문자열로 DB에 저장
    private AccountStatus accountStatus;
}
