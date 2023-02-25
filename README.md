## 개요
간단한 커머스 프로젝트

Use : Spring, Jpa, Mysql, Redis, Docker, AWS

Goal : 셀러와 구매자 사이를 중개해 주는 커머스 서버 구축

##회원
### 공통
- [x] 이메일을 통해서 인증번호로 인증 후 회원가입

### 고객
- [x] 회원가입
- [x] 인증 ( 이메일 )
- [x] 로그인 토큰 발행
- [x] 로그인 토큰을 통한 제어 확인 (JWT, Filter를 이용한 구현)
- [ ] 예치금 관리

### 셀러
- [ ] 회원가입

### 주문 서버

### 셀러
- [ ] 상품 CRUD
- [ ] 판매 내역 조회

### 구매자
- [ ] 장바구니를 위한 Redis 연동
- [ ] 상품 검색 & 상세 페이지
- [ ] 장바구니에 물건 추가
- [ ] 장바구니 확인
- [ ] 주문하기
- [ ] 주문내역 이메일로 발송하기


## 주문
- [ ] 장바구니
- [ ] 고객이 주문
