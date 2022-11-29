package com.example.account.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    // client와 controller간의 정보를 주고 받는 응답 dto
    private String accountNumber;
    private Long balance;
}
