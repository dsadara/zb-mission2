package com.example.account;

import lombok.experimental.UtilityClass;

// 유틸리티성 클래스
// 객체생성을 하지 않고 클래스는 껍데기일 뿐인 static 메소드만 있는 클래스
@UtilityClass   // private 생성자를 만들어줌
public class NumberUtil {
//    // 객체생성을 할 필요 없기 때문에 생성자를 private로 함
//    private NumberUtil(){
//    }

    public static Integer sum(Integer a, Integer b) {
        return a + b;
    }

    public static Integer minus(Integer a, Integer b) {
        return a - b;
    }
}
