package com.example.account.exception;

import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountException extends RuntimeException{
    // 커스텀 익셉션의 개수를 줄이기 위해 에러 코드와 에러 메시지를 너
    private ErrorCode errorCode;
    private String errorMessage;

    public AccountException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescripiton();
    }
}
