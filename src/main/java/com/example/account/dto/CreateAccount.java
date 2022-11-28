package com.example.account.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

// AccountController의 createAccount 엔드포인트의 dto
// Request, Response dto를 따로 만들지 않고 이너 클래스를 사용
public class CreateAccount {

    @Getter
    @Setter
    public static class Request {
        @NotNull
        @Min(1)     // 프로퍼티를 validation
        private Long userId;

        @NotNull
        @Min(100)
        private Long intialBalance;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long userId;
        private String accountNumber;
        private LocalDateTime registeredAt;
    }
}
