## Hello JPA 프로젝트 요약

---


### [Persistence class에서 설정 정보 조회]
- META-INF/persistenc.xml 참조 → JPA만 사용할 때 사용하는 설정 파일 
- Spring boot에서는 application.properties에서 설정 가능

### [JPA에서 사용하는 객체]
- EntityManagerFactory
  - EntityManager를 관리하는 객체
  - DB당 하나만 생성되는 개념으로 생각, 어플리케이션 전체에서 공유할 수 있도록 설계해야한다.
- EntityManager
  - DB 트랜잭션 제어 등 DB 접근시 해야하는 행동들을 제어할 수 있다.
  - DB 커넥션 개념과 동일하다고 본다.
  - CRUD등에 대응되는 메서드를 제공
- Entity
  - 테이블과 동일한 구성의 클래스를 작성하고 @Entity 어노테이션을 지정한다.
  - 컬럼을 어노테이션(@ID, @Column 등으로 지정)을 지정하여 필드와 맵핑한다.
  - 테이블과 맵핑되며, JPA를 활용한 DB 접근 시, 엔티티가 활용된다.

### [JPQL]
- JPA를 사용하면 엔티티 객체를 중심으로 개발하게 되는데, 그 때 검색 쿼리가 문제가 된다.
- JPA에서 쿼리를 사용할 수 있도록 해주는 기능정도로 생각하자.

### [기타 포인트]

- JPA의 모든 데이터변경은 트랜잭션 안에서 실행된다. 
- Update의 경우, 명시적으로 메서드를 호출하지 않아도, 변경점을 JPA에서 인지하고 있어 자동으로 쿼리가 발행된다.

