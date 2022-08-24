package jpashop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ORDER_ID", nullable = false)
    private Long orderId;

    @Column(name = "ITEM_ID", nullable = false)
    private Long itemId;

    private int orderPrice;
    private int count;
}
