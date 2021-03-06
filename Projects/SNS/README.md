# SNS

## 설명

Spring Boot, JPA, JWT를 이용해서 SNS api 만들기

## 개발 환경

- OS - macOS(ARM - M1)
- Editor - IntelliJ IDEA
- Version
    - JDK - 11(Zulu 11.0.12)

## dependencies

```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'io.jsonwebtoken:jjwt:0.9.1'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    runtimeOnly 'mysql:mysql-connector-java'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.security:spring-security-test'
}
```

## DB ERD

![ERD](erd.png)

## ToDo list

- [x] DB(Entity) 구성
- [x] JWT를 이용한 Spring Security
- [x] User 로그인, 로그아웃, 회원가입
- [x] User 끼리 Follow, UnFollow
- [x] 글 CRUD
- [x] 댓글과 대댓글 CRUD
- [x] 좋아요 기능
