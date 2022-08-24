package association.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "TEAM")
@Entity
public class Team {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;
}
