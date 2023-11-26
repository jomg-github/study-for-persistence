## JDBC(Java Database Connectivity) API

----

![jdbc api](/src/main/resources/images/jdbcapi.png)

- Java에서 제공하는 DB 접근을 위한 API
- JDBC API는 인터페이스만 제공해주는 것이고, 실제 구현은 각 DBMS 벤더들이 자신의 DB에 맞게 구현하여 제공하며 이를 'JDBC 드라이버' 라고 함
- Raw Query를 작성해서 쿼리를 요청
- DB Connection을 개발자가 직접 관리해야 하기 때문에 반복적인 코드 작업이 많음
- HikariCP와 같은 별도의 커넥션 풀 라이브러리를 통합 가능
- 쿼리 실행 결과를 ResultSet 객체로 반환 받아 직접 객체로 매핑

### 주요 클래스 및 인터페이스
  - DriverManager
    - Java에서 구현되어 제공하는 클래스
    - 주로 Driver 통해 DB Connection을 생성하여 반환해주는 역할
    - Driver 클래스가 반드시 필요함
  - Driver
    - DB와 connect를 실제로 수행하는 역할을 하는 인터페이스
    - 각 DBMS 벤더에서 해당 인터페이스를 구현한 Driver를 제공
  - Connection
    - Session, host, user 등 특정 DB의 연결 정보를 가지는 인터페이스
  - PreparedStatement
    - SQL을 미리 컴파일하여 실행 속도를 높이는 인터페이스
  - ResultSet
    - SQL문의 실행 결과를 조회할 수 있는 방법을 정의한 인터페이스

<br/>

## Spring JDBC Template

----

![jdbc template](/src/main/resources/images/jdbctemplate.png)
![simple jdbc insert](/src/main/resources/images/simplejdbcinsert.png)

- Spring에서 제공하며 JDBC API를 편하게 다루기 위한 기술
- 커넥션 등의 리소스 관리를 자동으로 수행해줌
- 커넥션 풀을 사용해서 성능을 최적화
- Raw Query를 작성해서 쿼리를 요청
- ResultSet을 객체로 변환하는 RowMapper 작성이 필요함
- 내부적으로는 JDBC API 활용하여 동작 

<br/>

## Spring Data JDBC

----

![simple jdbc repository](/src/main/resources/images/simplejdbcrepository.png)

- Spring Data 모듈에서 제공하는 경량 ORM
- CRUDRepository<T, ID>를 인터페이스를 구현하는 방식으로 사용
- 기본적인 CRUD 쿼리를 직접 작성할 필요 없음
- CRUDRepository의 실제 구현체는 SimpleJdbcRepository
- POJO 기반의 클래스를 통해 손쉽게 도메인 모델을 구성
- 일반적인 ORM과는 달리 별도의 PersistContext는 존재하지 않음
- 쓰기 지연, 1차 캐시, 더티 체킹 등의 기능은 지원하지 않음
- 복잡한 쿼리의 경우 직접 SQL을 작성하거나, Querydsl과 같은 추가 라이브러리를 활용
- 내부적으로는 Spring JdbcTemplate 사용됨

<br/>

## Spring Data JPA

----

![spring data jpa](/src/main/resources/images/springdatajpa.png)
![simple jpa repository](/src/main/resources/images/simplejparepository.png)
![hibernate jpa implements](/src/main/resources/images/hibernatejpaimplements.png)


- Spring Data 모듈에서 제공하는 ORM
- JPA 및 JPA 구현체를 편하게 다루기 위한 기술
- CRUDRepository<T, ID>를 구현한 JpaRepository<T, ID> 인터페이스를 구현하는 방식으로 사용
- JpaRepository의 실제 구현체는 SimpleJpaRepsitory
- EntityManager에 의해 persistence가 수행됨
- Hibernate는 EntityManager 인터페이스를 SessionImpl로 구현함
- Hibernate는 IdentityHashMap 타입의 PersistContext 인터페이스를 캐시로 구현
- 쓰기 지연, 1차 캐시, 더티 체킹 기능을 지원
- JQPL 지원
- 내부적으로는 JDBC API 기반으로 동작함