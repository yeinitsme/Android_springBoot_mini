# Android-SpringBoot Mini Project

## 1. 프로젝트 소개

Android 앱과 Spring Boot 서버를 연동하여 메모를 관리하는 간단한 CRUD 연습 프로젝트입니다.

모바일 프로그래밍 팀 프로젝트에 본격적으로 착수하기 전, Android 앱 개발과 서버 구축, 데이터베이스 연동 및 REST API 통신 과정을 미리 경험하고 학습하기 위해 제작했습니다.

추후 다른 프로젝트에서 서버 구축 및 앱과 서버 간 통신 구조를 참고하고 재활용하기 위한 목적으로 보관합니다.

## 2. 주요 기능 및 구현 현황

메모를 생성하고 조회하며, 수정 및 삭제할 수 있는 기능을 구현했습니다.

| 기능 | Android 앱 | Spring Boot 서버 |
|---|---|---|
| 메모 저장 | 구현 완료 | 구현 완료 |
| 메모 목록 조회 | 구현 완료 | 구현 완료 |
| 메모 수정 | 미구현 | 구현 완료 |
| 메모 삭제 | 미구현 | 구현 완료 |

※ 메모 수정 및 삭제 API는 서버에서 구현하고 Postman으로 테스트까지 완료했으나, Android 앱과의 연동은 진행하지 않았습니다.

## 3. 기술 스택

### Android
- Java
- Android Studio
- Retrofit
- Gson

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Gradle

### Database
- MySQL

### API 테스트
- Postman

## 4. 프로젝트 구조 및 통신 방식

- Android: 사용자 인터페이스 및 메모 관리 기능
- Spring Boot: 메모 관련 REST API 제공
- Retrofit: Android 앱과 Spring Boot 서버 간 HTTP 통신
- Spring Data JPA: 데이터베이스 연동 및 데이터 관리
- MySQL: 메모 데이터 저장

## 5. 실행 및 환경 설정

### 5-1. Android 앱 서버 연결 설정

Android 앱에서 연결할 Spring Boot 서버 주소는 `local.properties` 파일에서 관리합니다.

프로젝트를 처음 실행하는 경우:

1. `local.properties.example` 파일을 참고합니다.
2. Android 프로젝트 최상위 경로에 `local.properties` 파일을 생성합니다.
3. `API_BASE_URL`에 자신의 개발 환경에 맞는 서버 주소를 입력합니다.

예시:

```properties
API_BASE_URL=http://10.0.2.2:8080/
```

- Android Emulator: `10.0.2.2` 사용
- 실제 스마트폰: 개발 PC의 IPv4 주소 사용

※ `local.properties`는 개인 환경 설정 파일이므로 GitHub에 업로드하지 않습니다.

### 5-2. MySQL 데이터베이스 설정

Spring Boot 서버 실행 전 MySQL 설정이 필요합니다.

1. MySQL을 설치하고 실행합니다.
2. `submate` 데이터베이스를 생성합니다.
3. `application.properties`의 데이터베이스 연결 설정을 자신의 MySQL 환경에 맞게 확인합니다.

현재 프로젝트의 데이터베이스 설정은 다음과 같습니다.

- 데이터베이스 이름: `submate`
- 사용자 이름: `root`
- 비밀번호: 환경변수 `DB_PASSWORD` 사용

### 5-3. Spring Boot 환경변수 설정

MySQL 비밀번호는 `application.properties`에 직접 작성하지 않고 `DB_PASSWORD` 환경변수로 관리합니다.

IntelliJ IDEA 기준 설정 방법:

1. `Run` → `Edit Configurations` 클릭
2. Spring Boot 실행 설정 선택
3. `Environment variables` 항목에 다음 값 등록

```text
DB_PASSWORD=본인의_MySQL_비밀번호
```

4. 설정을 저장한 후 Spring Boot 서버를 실행합니다.

※ 실제 DB 비밀번호는 GitHub에 업로드하지 않습니다.

## 6. 프로젝트 활용 목적

- Android와 Spring Boot 간 통신 구조 참고
- Retrofit을 활용한 API 연동 방식 재활용
- Spring Boot REST API 및 JPA 사용 방식 참고
- MySQL 데이터베이스 연동 구조 참고
- 추후 팀 프로젝트의 기본 개발 환경 구성 시 참고 자료로 활용