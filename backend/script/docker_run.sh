./gradlew clean
#./gradlew clean
#./gradlew build buildDocker
#
## 이미지 확인(이미지아이디, 리파지토리명, 태그 확인)
#docker images -a
#
## 컨테이너 실행
#docker run -p 로컬포트:8080(톰캣포트) --name 컨테이너명 -t 리파지토리명:태그
#docker run -p 8883:8080 --name demo -t com.example.test/demo:0.0.1-SNAPSHOT
#
## 컨테이너 연결 종료
#control(⌃) + z
#
## 컨테이너 아이디 확인
#docker ps -a
#
## 컨테이너 정지 및 삭제
#docker stop 컨테이너아이디
#docker rm 컨테이너아이디

./gradlew build
docker build -t homepage .

# 컨테이너 실행
docker run -d -p 8080:8080 -i -t --name homepage homepage:latest /bin/bash
