package mongnam.spring.shoppingmall.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import mongnam.spring.shoppingmall.entity.Member;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberFormDto {

    /*
    NotNull/Null : Null 체크
    NotEmpty : Null 체크 및 문자열의 경우 길이 0인지 검사
    NotBlank : Null 체크 및 문자열의 경우 길이 0인지 + 빈 문자열(" ")인지 검사
    Length(min,max) : 길이 검사
    Email : 이메일형식인지 검사
    Max(숫자) : 최대값 검사
    Min(숫자) : 최소값 검사
     */

    @NotBlank(message = "이름은 필수입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 최소 4자 최고 16자입니다.")
    private String password;

    @NotEmpty(message = "주소는 필수입력 값입니다.")
    private String address;

}
