# 엔티티 맵핑

## 1. 객체와 테이블 맵핑

---

### [엔티티 맵핑 소개]

- 객체와 테이블 맵핑 : @Entity, @Table
- 필드와 컬럼 맵핑 : @Column
- 기본 키 맵핑 : @Id
- 연관관계 맵핑 : @ManyToOne, @JoinColumn

### [@Entity]

- @ Entity가 붙은 클래스는 JPA가 관리하며, 엔티티라한다.
- JPA를 사용해서 테이블과 맵핑할 클래스는 @Entity가 필수이다.

### [엔티티 속성]

- name
    - @Entity(name = “Member”)와 같은 형태로 사용
    - JPA에서 사용할 엔티티 이름을 지정한다.
    - 기본값 : 클래스의 이름을 그대로 사용한다.
    - 같은 클래스 이름이 없으면 가급적 기본값을 사용한다.
- @Table
    - 엔티티와 맵핑할 테이블 지정
    - 지정할 수 있는 속성
        - name : 맵핑할 테이블 이름 ( default = 엔티티 이름을 사용)
        - catalog : 데이터베이스 catalog 맵핑
        - schema : 데이터베이스 schema 맵핑
        - uniqueConstraints (DDL) : DDL 생성시에 유니크 제약 조건 생성

### [주의]

- 기본 생성자가 필수이다. (JPA는 리플랙션을 사용하기 때문) 따라서 파라미터가 없는 public 또는 protected 생성자가 필수이다.
- final 클래스, enum, interface, inner 클래스를 사용하면 안된다.
- 저장할 필드에 final을 사용하면 안된다.

## 2. 데이터베이스 스키마 자동 생성

---

### [DDL관련 특징]

- DDL을 어플리케이션 실행 시점에 자동 생성
- 테이블 중심에서 객체 중심으로 관점이 바뀐다.
- 데이터베이스 방언을 활용해서 데이터베이스에 맞는 적절한 DDL 쿼리를 생성한다.

### [자동생성과 관련된 속성]

[hibernate.hbm2ddl.auto](http://hibernate.hbm2ddl.auto) property의 값을 하기와 같이 설정하여 원하는 동작을 취할 수 있다.

- create :  기존테이블 삭제 후 다시 생성 (DROP + CREATE)
- create - drop : create와 같으나 종료시점에 테이블 DROP
- update : 변경분만 반영 (운영DB에는 사용하면 안됨)
- validate : 엔티티와 테이블이 정상 맵핑되었는지만 확인
- none : 사용하지 않음

### [DDL 생성 중 추가 기능]

- 제약 조건의 추가
    - ex) 회원 이름 필수, 10자 초과하면 안됨
    - @Column(nuallable = false, length = 10)
- 유니크 제약 조건 추가
    - @Table(uniqueConstraints = {@UniqueConstraint(name = “NAME_AGE_UNIQUE”, columnNames = {”NAME”, “AGE”} )})
- DDL 생성 기능은 DDL을 자동 생성할때만 사용되고, JPA의 실행 로직에는 영향을 주지 않는다.
    - 상기 예제의 경우, Runtime에 변경되는 점은 없다.
    - 단순히 Alter table 쿼리가 발행된다.

### [주의]

- 이렇게 생성된 **DDL은 개발 장비에서만 사용해야한다.**
    - 운영장비의 고객 자산 DB의 내용이 삭제될 수 있다.
    - create, create-drop, update를 사용하면 안된다.
    - **따로 DDL을 관리하는것도 하나의 방안 + DDL을 발행할 수 있는 권한을 분리하여 따로 관리하자!**
- 개발 초기 단계에는 create | update를 사용
- 테스트 서버는 update 또는 validate
- 스테이징과 운영 서버는 validate | none을 권장

## 3. 필드와 컬럼 맵핑

---

### [실습 예제의 요구사항 추가]

- 회원은 일반 회원과 관리자로 구분해야한다.
- 회원 가입일과 수정일이 있어야한다.
- 회원을 설명할 수 있는 필드가 있어야한다. 이 필드는 길이 제한이 없다.

### [실습에서 사용한 어노테이션 정리]

- @Column : 컬럼 맵핑
- @Temporal : 날짜 타입 맵핑
- @Enumerated : enum 타입 맵핑
- @Lob : BLOB, CLOB 맵핑
- @Transient : 특정 필드를 컬럼에 맵핑하지 않음

### [@Column의 속성]

- name : 필드와 맵핑할 테이블의 컬럼 이름 (default = 객체의 필드 이름)
- insertable, updatable : 등록, 변경 가능 여부  (default = TRUE)
- nullable(DDL) : null 값의 허용 여부 설정.  false로 설정하면 DDL 생성 시에 not null 제약 조건이 붙는다.
- unique(DDL) : 한 컬럼에 간단히 유니크 제약 조건을 걸 때 사용한다.
    - @Table의 uniqueConstraints와 같아 보이지만, 이름이 랜덤하게 생성되기 때문에 잘 사용하지 않는다.
- columnDefinition(DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있다. (default = 필드의 자바 타입과 방언 정보를 사용함)
    - ex) varchar(100) default ‘EMPTY’
    - DB에 종속적인 부분들도 추가할 수 있다.
- length(DDL) : 문자 길이 제약조건, String 타입에만 사용한다. (default = 255)
- precision, scale(DDL) : precision은 소수점을 포함한 전체 자릿수를, scale은 소수의 자릿수를 설정한다. (default = (precision = 19), (scale = ))
    - BigDecimal 타입에서 사용한다. (Biginteger도 사용할 수 있다.)
    - double, float타입에는 적용되지 않는다.


### [@Enumerated 속성]

- value (default = EnumType.ORDINAL)
    - EnumType.ORDINAL : enum의 순서를 데이터베이스에 저장
    - EnumType.STRING : enum 이름을 데이터베이스에 저장
    - **EnumType.ORDINAL은 운영상에 사용하면 안된다.** → ENUM이 수정되어 순서가 변경되어도, 운영 DB에는 해당 변경부분이 반영되어있지 않기 때문. 데이터 마이그레이션 작업등이 필요해진다.


### [@Temporal]

- value
    - TemporalType.DATE : 날짜, 데이터베이스 data타입과 맵핑
    - TemporalType.TIME : 시간, 데이터베이스 time 타입과 맵핑
    - TemporalType.TIMESTAMP : 날짜와 시간, 데이터베이스 timestamp 타입과 맵핑된다.
- 날짜 타입(java.util.Date, java.util.Calendar)를 맵핑할 때 사용
- 참고 : LocalDate, LocalDateTime을 사용할때는 생략 가능 (최신 하이버네이트 지원)

### [@Lob]

- 데이터베이스 CLOB, BLOB 타입과 맵핑
- @Lob에는 지정할 수 있는 속성이 존재하지 않음
- 맵핑하는 필드 타입이 문자면 CLOB 맵핑, 나머지는 BLOB 맵핑
    - CLOB : String, char[], java.sql.CLOB
    - BLOB : byte[], java.sql.BLOB