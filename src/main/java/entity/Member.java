package entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Member {
    @Id
    private Long id;
    @Column(unique=true, length = 10)
    private String name;
    private int age;

    // JPA에서 리플렉션을 사용하기 때문에 기본 생성자가 필수적이다.
    public Member() {
    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
        this.age = 10;
    }
}
