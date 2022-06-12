# PC_room


### 1. 주제
- PC방 시스템을 콘솔로 구현해보자

### 2. 개요(주제/목적)
- 남녀노소 자주 이용하는 PC방의 전반적인 시스템을 콘솔로 만들어보기

### 3. 환경
- JDK 8
- IntelliJ
- Windows 11
- GitHub

### 4. 서비스 설계 
![PCroom](https://user-images.githubusercontent.com/98489230/173229719-22854a3f-c1fc-4d33-a874-b3d77a14d83a.png)

-----

## 각 클래스별 설명

### 1. Main
- 메인 메소드


### 2. DB클래스
- 회원정보 파일처리
- 상품정보 파일처리
- 구매정보 파일처리

### 3. 회원클래스
- 회원 ID
- 회원 PW
- 회원 이름
- 회원 전화번호
- 회원 잔여시간 
- 회원 자리번호
- 회원 PC사용유무

### 4. 상품 클래스
- 상품 이름
- 상품 재고
- 상품 가격

### 5. 상품사용 클래스
- 구입한회원 아이디
- 구입한상품 이름
- 구입한상품 재고
- 구입한상품 가격

### 6. PC사용 클래스
- 멀티 스래드를 이용한 시간 감소

### 7. 컨트롤러
- 회원컨트롤 ( 회원가입, 로그인, 정보변경, 찾기, 탈퇴 )
- 일반유저 컨트롤 ( 시간추가, 자리이동, 결제, 상품선택등등 )
- 관리자 컨트롤 ( 강제 로그아웃, 매출확인, 회원 목록확인 등등)

