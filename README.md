# docker_project

로컬로 스프링을 작업하여 가상환경에서 배포할때 도커 컨테이너를 통해서 배포하는 과정입니다



09-19
문제 : 도커에 이미지 만들고 컨테이너 올리고 테스트 시도 경우, db 접근 오류 
해결시도 1 : 기존의 yml 파일이 dev 환경 (127.0.0.1)root 계정기준이여서, 도커(172.17.0.1) 환경에서 안된는 것확인 
도커 환경에서 접근 가능한 환경인 prod yml 파일 생성후 시도, 동일하게 접근 시도 후 db 관련 오류는 없는 것확인

문제 2 : 문제 1 해결 후에도 해당 포트로 접근이 안되는것 확인,
해결시도 2 : 도커로 컨테이너 안하고 host 환경에서 시도시 정상적으로 통신 되는 것 확인, 




activeProfile=prod ./gradlew clean build



yml 파일에 active : {activeProfile:dev} 로 설정해서 따로 지정을 안하면 dev 로 하고 위에 명령어처럼 
실행하면 prod 로 실행한다. 


docker logs -f spring-db-docker-0919_5 : 실시간 로그 



해결 :  8080:8080 으로 변경하여 컨테이너 빌드 하니까 정상적으로 되는것 확인
원인 : 컨테이너 포트인 8080 톰캣을 통해 접근을 해야하는데 톰캣 포트를 지정을 안해줘서 생긴 오류



보다 더 도커를 활용한 과정
1. nginx proxy manager (/docker_projects/nginx_2)
   1. vim docker-compose.yml 만들기
   

도커로 배포하는 과정 완료


##0920

오늘 젠킨스를 통해 배포를 한다.

커밋 => 푸시 => 깃헙웹훅 => 젠킨스로 신호

젠킨스 빌드
- 테스트
- 테스트 성공하면
- 기존의 앱 중지
- 새 앱 시작
# 젠킨스 실행

## 사전 준비
1. 기존에 호스트에 담긴 정보들 git pull origin master 함, =>
   1. 충돌이 났을경우 => git fetch , 작성한 파일들 commit 그 다음 merge 를 하자
   2. 완료 되었으면 다시 빌드 앤 런 을 하자 

## 명령어 정리
# 서버배포/운영

## 프로젝트 폴더 경로
- /docker_projects/sbdb_1/project

## 프로젝트 새로 구성
- rm -rf /docker_projects/sbdb_1/project
- mkdir -p /docker_projects/sbdb_1/project
- cd /docker_projects/sbdb_1/project
- git clone https://github.com/jhs512/app20220916_3 .
- chmod 744 ./gradlew

## 그래들 빌드
- ./gradlew clean build

## 현재 실행중인 컨테이너 중지 및 삭제
- docker stop sbdb_1
   - 안되면 : docker kill sbdb_1
- docker rm -f sbdb_1

## 새 sbdb 이미지 만들기
- git pull origin master
- 그래드 빌드
- docker build -t sbdb .

## sbdb 이미지 실행
```
docker run \
  --name=sbdb_1 \
  -p 8080:8080 \
  -v /docker_projects/sbdb_1/volumes/gen:/gen \
  --restart unless-stopped \
  -e TZ=Asia/Seoul \
  -d \
  sbdb
```




#젠킨스 설치

```
docker run \
-d \
-p 8081:8080 \
--restart unless-stopped \
-e TZ=Asia/Seoul \
-v /docker_projects/jenkins_1/var/jenkins_home:/var/jenkins_home \
-v /var/run/docker.sock:/var/run/docker.sock \ // 도커 환경에서 안쪽 젠킨스로 들어가서 조작 가능하게 
-v /docker_projects/nginx_1/data:/data \
--name jenkins_1 \
-u root \
jenkins/jenkins:lts 
```


- 젠킨스 따라 하기
    1. [http://url](http://url) ip 접근:8081(프로그램 설치후, 계정 설정 )
    2. 프로젝트 설치후 생성
    3. 프로젝트 만들기
    4. 빌드 후 콘솔 출력 결과

    ```bash
    Building in workspace /var/jenkins_home/workspace/basic_work_1
    ```
    5. 프라이빗 프로젝트는 토큰 받아서 설정해라 
    6. ngrok 설치 및 생성 