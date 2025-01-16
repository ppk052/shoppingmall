package mongnam.spring.shoppingmall.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ParamDto {

    private String name;

    private int age;
}
