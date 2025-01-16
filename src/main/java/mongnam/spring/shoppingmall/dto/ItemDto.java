package mongnam.spring.shoppingmall.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import lombok.*;
import mongnam.spring.shoppingmall.constant.ItemSellStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
public class ItemDto {

    private Long id;

    private String itemName;

    private int price;                      //가격

    private int stockNumber;                //재고수량

    private String itemDetail;              //상품상세설명

    private String itemSellStatus;          //상품의 판매 상태

    private LocalDateTime regTime;          //상품등록시간

    private LocalDateTime updateTime;       //상품수정시간
}
