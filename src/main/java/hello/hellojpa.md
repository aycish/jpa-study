## Hello JPA 프로젝트 요약

---

### [Persistence class에서 설정 정보 조회]

- META-INF/persistenc.xml 참조 → JPA만 사용할 때 사용하는 설정 파일
- Spring boot에서는 application.properties에서 설정 가능
- gradle 빌드 시, 클래스 인식이 자동으로 안될 때가 있다. (Unknown Entity) persistence.xml에서 persistence-unit에 class를 명시하여 해결할 수 있다.

### [JPA에서 사용하는 객체]

- EntityManagerFactory
  - EntityManager를 관리하는 객체
  - **DB당 하나만 생성되는 개념으로 생각, 어플리케이션 전체에서 공유할 수 있도록 설계해야한다.**
- EntityManager
  - DB 트랜잭션 제어 등 DB 접근시 해야하는 행동들을 제어할 수 있다.
  - **DB 커넥션 개념과 동일하다고 본다.**
  - CRUD등에 대응되는 메서드를 제공
- Entity
  - 테이블과 동일한 구성의 클래스를 작성하고 @Entity 어노테이션을 지정한다.
  - 컬럼을 어노테이션(@ID, @Column 등으로 지정)을 지정하여 필드와 맵핑한다.
  - 테이블과 맵핑되며, JPA를 활용한 DB 접근 시, 엔티티가 활용된다.


### [JPQL]

- JPA를 사용하면 엔티티 객체를 중심으로 개발하게 되는데, 그 때 검색 쿼리가 문제가 된다.
- JPA에서 쿼리를 사용할 수 있도록 해주는 기능정도로 생각하자.
- EntityManager의 createQuery를 사용한다.
  - Pagenation 지원 (setFirstResult, setMaxResult 등으로)

### [기타 포인트]

- **JPA의 모든 데이터변경은 트랜잭션 안에서 실행된다.**
- Update의 경우, 명시적으로 메서드를 호출하지 않아도, 변경점을 JPA에서 인지하고 있어 자동으로 쿼리가 발행된다.

## 실습

---

### [DB]

- 사전에 쿼리 발행으로 엔티티의 필드에 맞춰 생성해 놓는다.
- Member 테이블 (Bigint ID, varchar Name 컬럼) 생성

### [Main]

- EntityManagerFactory 생성
- EntityManager 객체 생성
- EntityManager에서 원하는 동작 수행
  - transaction begin
  - 질의 시작 (CRUD)
  - trasaction rollback | commit 등 수행
- EntityManager close
- EntityManagerFactory close

### [CRUD]

- Create
  - 엔티티 객체 생성 및 값 세팅
  - EntityManager로 persist 수행
- Read
  - EntityManager에서 find 메서드 수행
- Update
  - EntityManager의 find 메서드로 DB로부터 데이터를 로딩해서 원하는 객체 조회
  - set Method 등으로 수정
  - 따로 persist하지 않아도,  JPA에서 관리하고있는 영속성 테이블과 비교, 값이 변경되면 자동으로 Update 쿼리를 발행한다.
- delete
  - find 메서드로 DB에서부터 로딩
  - remove 메서드 수행