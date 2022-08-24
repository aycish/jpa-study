package jpashop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CLIENT")
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
