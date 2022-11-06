# README.md

```markdown
├── LiveApplication.java
├── aop
│   └── LoggingAspect.java
├── controller
│   ├── AdminUserController.java
│   ├── AptController.java
│   ├── BoardController.java
│   ├── DongCodeController.java
│   ├── ExceptionController.java
│   ├── HomeController.java
│   ├── PageController.java
│   └── UserController.java
├── interceptor
│   ├── ConfirmInterceptor.java
│   └── ConfirmInterceptor2.java
├── model
│   ├── dto
│   │   ├── Apt.java
│   │   ├── Board.java
│   │   ├── DongCode.java
│   │   ├── FileInfo.java
│   │   ├── HouseInfo.java
│   │   ├── Interest.java
│   │   └── User.java
│   ├── mapper
│   │   ├── AptMapper.java
│   │   ├── BoardMapper.java
│   │   ├── DongCodeMapper.java
│   │   └── UserMapper.java
│   └── service
│       ├── AptService.java
│       ├── AptServiceImpl.java
│       ├── BoardService.java
│       ├── BoardServiceImpl.java
│       ├── DongCodeService.java
│       ├── DongCodeServiceImpl.java
│       ├── UserService.java
│       └── UserServiceImpl.java
└── util
    ├── PageNavigation.java
    └── SizeConstant.java
```

## ERD

---

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled.png)

## 🌈 메인 화면 페이지

### 화면 구성 - body

- **header**
    - **nav**
        - home/아파트 매매 정보/소개/공지사항/Log In
- **hero** : Main Section과 header 사이에 홈페이지의 대표 이미지와 글을 담은 section
- **Main Section**
    - **Client** : 한국의 고급 아파트 브랜드 로고 배치 및 고객사를 담는 section
    - **About us** : 홈페이지의 소개를 담당하는 section
    - **Services** : 아파트 관련 인터넷 기사의 내용 및 링크 제공 section
    - **Cta** : 고객 대응 section
- **Footer**
- 모든 데이터 처리는`Rest APi`기반으로 이루어졌다.

### 메뉴바 Log In Slide Down

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%201.png)

### 메뉴바 Log In Slide Down - admin(관리자)가 로그인 할 때

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%202.png)

### Header , Hero Section , Client Section

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%203.png)

### About Us Section

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%204.png)

### Services Section

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%205.png)

### Cta Section , Footer

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%206.png)

## 🌈 아파트 매매 정보 페이지

<select> <option>태그를 통해 시도 구군 동 선택 및 년도,월 별 아파트 매매 정보를 확인할 수 있다.

왼쪽 시도부터 선택 시 해당하는 구군, 동이 JavaScript를 통해 옵션 별로 지정되고 이를 아파트 매매 정보 가져오기 버튼 클릭을 통해 카카오맵과 table 형태로 확인이 가능하다.

### 선택 전

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%207.png)

### 선택 후

관심지역 등록 설명

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%208.png)

## 🌈 로그인 페이지

우측 상단 Log In 버튼으로 로그인 페이지로 이동하여 아이디와 비밀번호를 입력해 로그인을 수행할 수 있다. 

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%209.png)

## 🌈 회원관리 페이지

우측 상단 Log In 버튼으로 회원가입 페이지로 이동하여 아이디, 비밀번호, 이름, 주소, 전화번호를 입력해 회원가입을 수행할 수 있다.

- 동기(Post방식으로 controller에 정보가 전달되고 DB에 추가된다.)

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2010.png)

## 🌈 회원 정보 확인/탈퇴 페이지

우측 상단 Log In 버튼으로 회원 정보 확인 페이지로 이동하여 로그인 되어있는 회원 정보를 확인할 수 있다.

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2011.png)

## 🌈 회원 정보 수정 페이지

회원 정보 확인 페이지에서 이동하여 회원 정보를 수정할 수 있다.

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2012.png)

## 🌈 비밀번호 찾기 페이지

- Modal을 이용하여 Rest Api로 해당 데이터를 보내 일치하는 정보가 있다면 alert로 해당 비밀번호를 알려준다.

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2013.png)

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2014.png)

## 🌈 관심매물 확인 페이지

- 관심매물 등록 페이지로 이동하여 아파트 매매 정보 페이지에서 추가한 즐겨찾기 매물을 확인 할 수 있다.
- 삭제 버튼을 두어 해당하는 관심매물의 삭제기능도 제공합니다.

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2015.png)

## 🌈 공지사항 및 게시판 페이지

- 우측 상단 공지사항을 클릭하면 공지사항 게시판을 확인할 수 있다. 글쓰기 버튼을 통해 게시판 글쓰기가 가능 - 공지글은 로그인 시 관리자만 글을 쓸 수 있다.

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2016.png)

- 글쓰기 버튼을 클릭하여 게시판에 글을 쓸 수 있다.

### 🌈게시글 조회

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2017.png)

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2018.png)

### 🌈게시글 검색 - 제목, 작성자

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2019.png)

### 🌈게시글 작성

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2020.png)

### 🌈게시글 상세 조회

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2021.png)

### 🌈게시글 수정

![Untitled](README%20md%20bb3616350eee4745bd543347886cf3f5/Untitled%2022.png)