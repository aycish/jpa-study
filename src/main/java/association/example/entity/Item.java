package association.example.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ITEM")
@SequenceGenerator(name = "ITEM_SEQ_GENER", sequenceName = "ITEM_SEQ", initialValue = 1, allocationSize = 50)
public class Item {

    @Id
    @Column(name = "ITEM_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_SEQ_GENER")
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private Long price;

    @Column(name = "STOCKQUANTITY")
    private Long stcokQuantity;
}
