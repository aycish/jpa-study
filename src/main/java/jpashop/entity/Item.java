package jpashop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM")
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
