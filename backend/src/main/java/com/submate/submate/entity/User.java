package com.submate.submate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//이 클래스는 DB에 저장할 데이터의 형태라는 뜻
@Entity
public class User {
    //이 변수를 DB 테이블의 기본키로 사용하겠다는 표시
    @Id

    /*
    @GeneratedValue : 기본키 값을 자동으로 생성하겠다는 표시
    GenerationType.IDENTITY : MySQL의 자동 증가 기능을 이용하겠다는 설정
    */

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String major;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}


