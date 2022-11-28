package com.example.account.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)// @CreatedDate, @LastModifiedDate
public class AccountUser {
    // 우리가 유저 api를 만들 것은 아니기 때문에 간단히 만들 것임
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 모든 테이블에 갖고 있으면 좋은 메타 정보
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
