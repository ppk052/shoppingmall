package mongnam.spring.shoppingmall.entity;

import jakarta.persistence.*;
import org.hibernate.Length;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id")
    private Long id;

    private int age;

    @Column(length = 30)
    private String name;
}
