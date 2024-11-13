package mongnam.spring.shoppingmall.repository;

import mongnam.spring.shoppingmall.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String itemName);
    
}
