* 개발 프레임워크 : 
  - Spring boot 
  - Maven 기반
  - 인메모리 DB(h2)
  - JPA

* 문제해결 방법 :

* 빌드 및 실행 방법
1. maven build package
2. /target폴더로 이동
3. jar -jar searchAPI-0.0.1-SNAPSHOT.jar
4. http://localhost:7777/console -> h2 내장디비 실행
5.데이터 파일에서 각 레코드를 데이터베이스에 저장 (POST방식)
  http://localhost:7777/files/upload 
    form-data
      files : 사전과제1.csv ,
      msg   : "Region"
6. 지원하는 지자체 목록검색 (POST방식)
  http://localhost:7777/region/search
  post 전송 
  
7.지원하는 지자체명을 입력받아 해당 지자체의 지원정보 출력(POST방식)
  post 전송
  http://localhost:7777/region/search
  -json 입력
  {
    "region" : "강릉시"
  }
  
8. 지원하는 지자체 정보 수정(POST방식)
  http://localhost:7777/region/edit/reg0001
  -json 입력
  {
    "rate" : "4%",
    "institute" : "강릉시22",
    "mgmt" : "강릉지점22",
    "reception" : "강릉시 소재 영업점22"
  }
