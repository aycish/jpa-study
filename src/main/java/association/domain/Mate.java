package association.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "MATE")
public class Mate {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATE_ID")
    private Long id;

    // 객체 지향적으로 설계하려면 하기 방법은 피해야한다.
    //@Column(name = "TEAM_ID")
    //private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Column(name = "USERNAME")
    private String userName;
}
