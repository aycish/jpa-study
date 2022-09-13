package association.example.entity.order;

import association.example.entity.Person;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SequenceGenerator(name = "ORDER_SEQ_GENER", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 50)
public class Orders {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENER")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDERDATE")
    private LocalDate orderDate;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    @OneToMany(mappedBy = "orders")
    private List<OrderItem> orderItemList = new ArrayList<>();

    public void changePerson(Person person) {
        this.person = person;
        person.getOrdersList().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItemList.add(orderItem);
        orderItem.setOrders(this);
    }
}
