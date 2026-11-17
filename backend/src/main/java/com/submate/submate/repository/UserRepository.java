package com.submate.submate.repository;

import com.submate.submate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
    User.java: 어떤 데이터를 저장할지 정의한 설계도
    UserRepository.java: DB 저장·조회 기능을 제공하는 창구
 */
public interface UserRepository extends JpaRepository<User, Long> {

}