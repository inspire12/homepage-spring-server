master

# Homepage Spring Server for private project

DEMO: http://cnuant.iptime.org:8080/

REF: http://cnuant.iptime.org:8080/opensource

### Learn
#### Goal 
1. 읽기 쉬운 코드 
    - 글 읽듯 읽혀야 한다.(변수 이름 의미있게 정하기, 영어 문법 고려)
    - 일관성이 있어야한다.
2. 수정하기 쉬운 코드 
    - 변경의 여파가 적어야한다. (순수함수, 상태를 남기지 않음 구현을 감추기)
    - 버그를 최대한 빨리(컴파일 단에서) 발견하도록
####  1 week 
0) 개발의 방향성 
 - 이해하기 쉬운 코드: 코드가 짧고 단순하고 직관적인 코드
 - 수정하기 쉬운 코드: 변경의 여파가 적은 코드
 
1) 협업의 이해와 도구 사용 
 - Git, Git hub 
 - Git branch, Git flow
 
2) Spring boot 이해 
 - Main의 위치 ([application](https://github.com/inspire12/homepage-spring-server/blob/master/src/main/java/com/inspire12/homepage/HomepageApplication.java)
 - web front(html, css, js, img) 파일([resource](https://github.com/inspire12/homepage-spring-server/tree/master/src/main/resources)) 
 - Build 시스템 (gradle) 
 - Config ([spring config](https://github.com/inspire12/homepage-spring-server/tree/master/src/main/java/com/inspire12/homepage/config)) 
 
3) MVC 
 - Controller: 요청 제어 
 - Model: 데이터를 가져옴 
 - Service: 데이터를 가공함
 
+) 추가 
- intellij 단축키 
- 논리적구분, 물리적구분 이해 
   
####  2 week   
1) 개발 / 배포 서버 
 - /script/** (nohup ./gradlew bootrun --args='--spring.profiles.active=live' &) 
 - lsof -i:{port번호} 

2) 데이터 저장, 데이터 불러오기 
 - 데이터베이스 사용해보기 
 - SQL 문 써보기 
 - Repository 개념 
 - JPA & Hibernetes 맛보기
 
#### 3 week
1) 데이터 -> view 
 - Message: 데이터를 가공해 클라이언트에 보냄
 - View: 사용자에게 보여줌 (web front) 
 - JS의 component.js (message의 데이터를 view로 binding함)
 - template engine (https://www.thymeleaf.org/)

2) view -> 데이터 
 - Http의 전송방식 (put, post, delete)
 - Fetch API  

#### 4 week
1) 여러 Javascript 라이브러리 적용해보기  
 - Alime Template
 - CKEDITOR 
 - Choice js 
 - Valid js 
  
2) Javascript 주의할점
 - js 기본 문법, 특징
 - hoisting 
 - scope 
 
#### 5 week 
1) 인증 구현 
 - Spring security 를 이용한 login 처리 
2) Interceptor 를 통한 인증 정보   
 - preHandle
 - postHandle 
3) annotation으로 함수 접근 범위 설정 
 - custom으로 annotation 생성하여 함수 범위 설정
 - 관점지향 코딩의 방향성 

#### 기술
1. Spring Web
2. Spring jpa 
3. Spring security 
4. Spring Template engine (Thymeleaf)
    - alime template
    - Bootstrap 
    - Js libraries   


