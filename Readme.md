# 게시판 프로젝트

### 프로젝트 참여자
---
개인 프로젝트
### 프로젝트 내용
----
> 회원 기능  
- ID, Password, Email의 정보를 가짐
- Email 양식 검증
- 로그인 
- 타 사이트 이동 후에도 로그인 유지 
- 비 로그인자는 로그인 화면 외에 접근 불가
- 가입, 수정, 탈퇴
- ID 기억하기

> 게시판 기능
- 1개의 게시판
- 목록은 글, 등록일, 등록자 출력
- 답글은 99개까지 가능 해야 함
- 답글 포함 20개 단위로 페이징
- 동일 Level의 글의 정렬은 최신 날짜 우선
- 글에 대한 권한 처리 (본인 글만 수정/삭제)
- 글자 수 제한은 200자
- 이미지 업로드(jpg, png, gif) 
- 입력 화면은 전부 직접 구현

### 사용 기술
----
    1. 백앤드
        - Java8
        - Spring boot
        - ORM(JPA)
        - MySQL

    2. 프론트
        - FreeMarker
        - JQuery
        - BootStrap
        
    3. 기타
        - Gradle
        - Git & Gitgub
        - Trello
        - Slack
        - Logger (SLF4J)
        - .gitignore