package entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MBR")
@Data
@SequenceGenerator(
        name = "NEW_MEMBER_SEQ_GENER",
        sequenceName = "NEW_MEMBER_SEQ",
        initialValue = 10, allocationSize = 50)  // create sequence NEW_MEMBER_SEQ start with 10 increment by 50 쿼리 발행
/* ID를 생성하는 테이블을 지정하는 방법. strategy를 TABLE로 지정해야한다.
@TableGenerator(
        name = "NEW_MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = “NEW_MEMBER_SEQ", allocationSize = 1)
*/
public class NewMember {

    @Id
    // 자동 생성하는 방법
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEW_MEMBER_SEQ_GENER")
    private Long id;

    // unique 제약 조건을 걸 수 있으나, 이름이 랜덤하게 생성되기 때문에 잘 사용하지 않는다.
    // 대신 @Table에서 uniqueConstraints를 사용한다. (이름등을 모두 설정할 수 있기 때문)
    @Column(name = "name", updatable = false, nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient
    private int testInteger;

    public NewMember() {}

}
