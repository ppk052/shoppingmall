package mongnam.spring.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.*;
import mongnam.spring.shoppingmall.constant.ItemSellStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(nullable = false, length = 50, name = "name_item")
    private String itemName;

    private int price;                      //가격

    private int stockNumber;                //재고수량

    //CLOB, BLOB 데이터가 길때 사용
    @Lob
    @Column(nullable = false)
    private String itemDetail;              //상품상세설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //상품의 판매 상태

    private LocalDateTime regTime;          //상품등록시간

    private LocalDateTime updateTime;       //상품수정시간
}
