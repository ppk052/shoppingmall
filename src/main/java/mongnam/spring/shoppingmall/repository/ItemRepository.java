package mongnam.spring.shoppingmall.repository;

import mongnam.spring.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String itemName);

    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    List<Item> findByPriceLessThan(int price);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    List<Item> findByPriceLessThanOrderByPrice(int price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc") //:는 아래 변수임을 표시
    List<Item> findByDetail(@Param("itemDetail") String itemDetail); //JPQL문

    @Query(value = "select * from Item where item_detail like %:itemDetail% order by price desc",nativeQuery = true) //:는 아래 변수임을 표시
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail); //Native문
    
}
