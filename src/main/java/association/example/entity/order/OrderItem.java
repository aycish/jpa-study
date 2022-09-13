package association.example.entity.order;

import association.example.entity.Item;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDER_ITEM")
@SequenceGenerator(name = "ORDER_ITEM_SEQ_GENER", sequenceName = "ORDER_ITEM_SEQ", initialValue = 1, allocationSize = 50)
public class OrderItem {

    @Id
    @Column(name = "ORDER_ITEM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_SEQ_GENER")
    private long id;

    @Column(name = "ORDERPRICE")
    private long orderPrice;

    @Column(name = "COUNT")
    private long count;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID")
    private OrderItem item;
}
