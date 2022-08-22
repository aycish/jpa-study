package entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MBR")
@Data
public class NewMember {
    @Id
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
