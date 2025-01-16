package mongnam.spring.shoppingmall;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import mongnam.spring.shoppingmall.constant.ItemSellStatus;
import mongnam.spring.shoppingmall.entity.Item;
import mongnam.spring.shoppingmall.entity.QItem;
import mongnam.spring.shoppingmall.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingmallApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

	@PersistenceContext
	private EntityManager em;

	@Test
	void createItemList(){
		for (int i = 1; i <= 10; i++) {
			Item item = Item.builder().itemName("테스트상품" + i).itemDetail("테스트입니다" + i).price(3000 + (i*100)).stockNumber(150+(i*10)).itemSellStatus(ItemSellStatus.SELL).updateTime(LocalDateTime.now()).regTime(LocalDateTime.now()).build();
			itemRepository.save(item);
		}
	}

	@Test
	@DisplayName("querydsl 테스트")
	void querydslTest(){
		createItemList();
		JPAQueryFactory query = new JPAQueryFactory(em);
		QItem qItem = QItem.item;

		List<Item> itemList = query.selectFrom(qItem)
				.where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
				.where(qItem.itemDetail.like("%" + "1" + "%"))
				.orderBy(qItem.price.asc())
				.fetch();

		itemList.forEach((item)->{
			System.out.println(item);
		});
	}

	@Test
	@DisplayName("boolean builder")
	void querydslTest2(){
		createItemList();

		BooleanBuilder builder = new BooleanBuilder();
		String itemDetail = "테스트";
		int price = 10004;
		String itemSellStatus = "SELL";

		QItem item = QItem.item;

		builder.and(item.itemDetail.like("%" + itemDetail + "%"));
		builder.and(item.price.gt(price));

		if(StringUtils.equals(itemSellStatus,ItemSellStatus.SELL)){
			builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
		}

		Pageable pageable = PageRequest.of(0, 5);

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

	@Test
	@DisplayName("OR 테스트")
	void findByItemNameOrItemDetail(){
		createItemList();
		List<Item> itemList = itemRepository.findByItemNameOrItemDetail("테스트상품2", "테스트입니다8");
		itemList.forEach((item)->{
			System.out.println(item);
		});
	}

	@Test
	@DisplayName("가격기준찾기 테스트")
	void findByPriceLessThanOrderByPriceDesc(){
		createItemList();
		itemRepository.findByPriceLessThanOrderByPriceDesc(3500).forEach((item)->{
			System.out.println(item);
		});
	}

	@Test
	@DisplayName("가격기준찾기 테스트 내림차순")
	void findByPriceLessThanOrderByPrice(){
		createItemList();
		itemRepository.findByPriceLessThanOrderByPrice(3600).forEach((item)->{
			System.out.println(item);
		});
	}

	@Test
	@DisplayName("JPQL테스트")
	void findByDetail(){
		createItemList();
		System.out.println("테스트1");
		itemRepository.findByDetail("테스트입니다10").forEach((item) -> {
			System.out.println(item);
		});
		System.out.println("테스트2");
		itemRepository.findByDetail("테스트입니다1").forEach((item) -> {
			System.out.println(item);
		});
	}

	@Test
	@DisplayName("Native 테스트")
	void findByDetailNative(){
		createItemList();
		System.out.println("테스트1");
		itemRepository.findByDetailNative("테스트입니다10").forEach((item) -> {
			System.out.println(item);
		});
		System.out.println("테스트2");
		itemRepository.findByDetailNative("테스트입니다1").forEach((item) -> {
			System.out.println(item);
		});
	}
}
