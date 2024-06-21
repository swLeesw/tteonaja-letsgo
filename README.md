## 👩‍💻 Enjoy Trip

<table align="center">
    <tr align="center">
        <td style="min-width: 150px;">
            <a href="https://github.com/MJ-Kor">
              <img src="https://avatars.githubusercontent.com/u/87603324?v=4" width="200">
              <br />
              <b>MJ-Kor</b>
            </a>
        </td>
        <td style="min-width: 150px;">
            <a href="https://github.com/swLeesw">
              <img src="https://avatars.githubusercontent.com/u/74245930?v=4" width="200">
              <br />
              <b>swLeesw</b>
            </a> 
        </td>
    </tr>
    <tr align="center">
        <td>
            김민주  
        </td>
        <td>
            이승원
        </td>
    </tr>
</table>

## ⚒️ 화면 구성

### 메인 화면
- 홈페이지 메인 화면 입니다. 
- 메인페이지 하단에서 인기있는 관광지, 게시판 별 인기 글을 사용자에게 보여줍니다.
![main](./docs/화면설계서/Main/main.png)
![main2](./docs/화면설계서/Main/main2.png)
- 
### 회원관리
#### 로그인
- 로그인 페이지 입니다.
- 유효하지 않은 값이 입력되면 '아이디 또는 패스워드를 확인하세요.'라는 경고창이 나타납니다.
![login.png](./docs/화면설계서/Login/login_fail.png)
- 로그인이 완료되면 헤더 부분에 로그인 버튼 대신 내 정보와 로그아웃이 나타납니다.
![loginResult.png](./docs/화면설계서/Login/login_success.png)

#### 회원가입 화면
- 회원가입 페이지 입니다.
- 아이디 중복 검사 실시 후 회원가입이 가능합니다.
- 조건에 맞지 않는 값을 입력했을 경우 가입이 되지 않습니다.
- 회원가입이 완료되면 로그인 페이지로 이동됩니다.
![signup.png](./docs/화면설계서/Register/alert_duple.png)
![signup.png](./docs/화면설계서/Register/id_available.png)
![pw.png](./docs/화면설계서/Register/pw_valid.png)
![signup.png](./docs/화면설계서/Register/id_valid.png)
![signup.png](./docs/화면설계서/Register/email_valid.png)
![signup.png](./docs/화면설계서/Register/regist_success.png)

#### 비밀번호 찾기
- 로그인 페이지에서 비밀번호 찾기를 누르면 모달창이 나타납니다.
- 비밀번호는 아이디와 이메일을 이용해 찾을 수 있습니다.
![findID_PW1.png](./docs/화면설계서/Login/find_pw.png)
- 아이디를 입력하면 해당 아이디로 회원가입된 이메일 주소로 인증번호가 제공됩니다.
![findID_PW2.png](./docs/화면설계서/Login/mail.png)
- 비밀번호 찾기 결과창입니다.
![findID_PW3.png](./docs/화면설계서/Login/find_pw_success.png)
![findID_PW3.png](./docs/화면설계서/Login/find_pw_fail.png)

#### 내 정보 조회, 수정
- 내 정보 화면입니다.
- 로그인 한 유저가 본인의 정보를 확인할 수 있습니다.
- 내 정보 페이지에 들어가기 전 비밀번호를 입력해야 합니다.
![mypage1.PNG](./docs/화면설계서/MyPage/pw_check.png)
- 내 정보에서 회원 정보를 수정 및 탈퇴 할 수 있습니다.
![mypage2.png](./docs/화면설계서/MyPage/pw_correct.png) 
![mypage3.png](./docs/화면설계서/MyPage/user_del.png)
![mypage3.png](./docs/화면설계서/MyPage/user_del_success.png)

### 관광지 검색 화면
- 관광지 조회 화면입니다.
- 지도에 관광지 위치에 마크가 찍히고 사진과 함께 설명이 출력됩니다.
- 관광지는 이름, 시/도, 군/구 별로 검색이 가능합니다.
![article1.png](./docs/화면설계서/AttractionSearch/attraction.png)
![article2.png](./docs/화면설계서/AttractionSearch/option.png)
![article3.png](./docs/화면설계서/AttractionSearch/name.png)
- 관광지에는 리뷰를 남길 수 있으며, 좋아요 버튼을 누를 수 있습니다.
![article5.png](./docs/화면설계서/AttractionSearch/review.png)
![article5.png](./docs/화면설계서/AttractionSearch/like.png)


### 게시판 구현
- 여행 코스 게시판과 자유게시판 입니다.

#### 여행 코스 게시판
- 여행 코스 게시판을 클릭하면 여행 코스 목록이 출력됩니다.
- 모든 로그인한 이용자가 게시글을 작성, 열람, 수정, 삭제 할 수 있습니다.
![plan1.PNG](./docs/화면설계서/AttractionCourse/course_main.png)
- 여행지는 관광지 검색 지도에서 직접 추가 및 삭제할 수 있으며, 중복 선택은 되지 않습니다.
![plan2.PNG](./docs/화면설계서/AttractionCourse/course_article.png)
![plan2.PNG](./docs/화면설계서/AttractionCourse/course_write.png)
- 여행 코스 게시글에는 댓글을 남길 수 있으며, 작성자 이외에 삭제는 불가합니다.
![plan2.PNG](./docs/화면설계서/AttractionCourse/comment_write.png)
![plan2.PNG](./docs/화면설계서/AttractionCourse/comment_del.png)

#### 자유 게시판
- 자유 게시판을 클릭하면 자유게시판 목록이 출력됩니다.
- 자유 게시판은 게시글 번호, 작성자 아이디, 제목으로 검색할 수 있습니다.
![free1.PNG](./docs/화면설계서/FreeBoard/search_id.png)
- 모든 로그인한 이용자가 게시글을 작성, 열람, 수정, 삭제 할 수 있습니다.
- 이용자는 자신이 쓴 글에대한 게시글 수정, 삭제 권한이 있습니다.
![free1.PNG](./docs/화면설계서/FreeBoard/free_write.png)
![free1.PNG](./docs/화면설계서/FreeBoard/free_detail.png)
![free1.PNG](./docs/화면설계서/FreeBoard/free_del.png)
- 자유 게시글에는 댓글을 남길 수 있으며, 작성자 이외에 삭제는 불가합니다.
![free1.PNG](./docs/화면설계서/FreeBoard/comment_write.png)
![free1.PNG](./docs/화면설계서/FreeBoard/comment_del.png)
- 여행 코스 게시글과 더불어 자유 게시글은 translate 버튼을 클릭하면 Chat gpt 기반의 한/영 번역이 가능합니다.
![free1.PNG](./docs/화면설계서/FreeBoard/free_translate.png)

## ⚒️ 기능 목록

[기능 목록 명세서](docs/usecase_diagram/function.md)

## ⚒️ 데이터베이스

[SCHEMA](docs/DB/tteonaja_db.sql)

![ERD](./docs/DB/tteonaja-ERD.png)

## ✅ 커밋 컨벤션

| 제목          | 내용                                              |
|-------------|-------------------------------------------------|
| init        | 작업 세팅 커밋 (패키지 설치 등)                             |
| feat        | 새로운 기능을 추가할 경우                                  |
| style       | 기능에 영향을 주지 않는 커밋, 코드 순서, css등의 포맷에 관한 커밋        |
| fix         | 버그를 고친 경우                                       |
| refactor    | 프로덕션 코드 리팩토링                                    |
| test        | 테스트 코드 작성                                       |
| docs        | 문서를 수정한 경우, 파일 삭제, 파일명 수정 등 ex) README.md       |
| chore       | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우, 주석 추가, 자잘한 문서 수정 |
| code review | 코드 리뷰 반영                                        |

---

## 📁 폴더 구조
