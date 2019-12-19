
./gradlew clean

# 이미지 확인(이미지아이디, 리파지토리명, 태그 확인)
docker images -a

./gradlew build
docker build -t homepage .

# 컨테이너 실행
docker run -d -p 8080:8080 -i -t --name homepage homepage:latest /bin/bash