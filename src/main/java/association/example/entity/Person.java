package association.example.entity;

import association.example.entity.order.Orders;
import jpashop.entity.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PERSON")
@Data
@SequenceGenerator(name = "PERSON_SEQ_GENER", sequenceName = "PERSON_SEQ", initialValue = 1, allocationSize = 50)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ_GENER")
    @Column(name = "PERSON_ID", nullable = false)
    private Long id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "CITY")
    private String city;
    @Column(name = "STREET")
    private String street;
    @Column(name = "ZIPCODE")
    private String zipCode;

    @OneToMany(mappedBy = "person")
    List<Orders> ordersList = new ArrayList<>();
}
