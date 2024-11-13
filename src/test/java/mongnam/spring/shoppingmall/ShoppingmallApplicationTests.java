package mongnam.spring.shoppingmall;

import mongnam.spring.shoppingmall.constant.ItemSellStatus;
import mongnam.spring.shoppingmall.entity.Item;
import mongnam.spring.shoppingmall.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ShoppingmallApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void createItemList(){
		for (int i = 1; i <= 10; i++) {
			Item item = Item.builder().itemName("테스트상품" + i).itemDetail("테스트입니다" + i).price(3000 + (i*100)).stockNumber(150+(i*10)).itemSellStatus(ItemSellStatus.SELL).updateTime(LocalDateTime.now()).regTime(LocalDateTime.now()).build();
			itemRepository.save(item);
		}
	}

	@Test
	void findByItemNameTest(){
		createItemList();
		itemRepository.findByItemName("테스트상품1").forEach((item) -> System.out.println(item));
	}

	@Test
	@DisplayName("상품생성테스트")
	void createItemTest() {
		Item item = Item.builder().itemName("테스트상품").itemDetail("테스트입니다2").price(3000).stockNumber(150).itemSellStatus(ItemSellStatus.SELL).updateTime(LocalDateTime.now()).regTime(LocalDateTime.now()).build();
		System.out.println("=======================================\nitem: "+item.toString());
		System.out.println(itemRepository.save(item));

	}

}
