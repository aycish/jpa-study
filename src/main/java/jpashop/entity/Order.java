package jpashop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
@Data
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "CLIENT_ID")
    // 아직 연관관계가 설정되어 있지 않기 때문에, 추후 실습에서 추가하도록 하자.
    private Long clientId;

    // ORDER_DATE | order_date @Column으로 지정 -> 팀의 성향에 따라 다르다.
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
