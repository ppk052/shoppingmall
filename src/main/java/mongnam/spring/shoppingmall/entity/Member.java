package mongnam.spring.shoppingmall.entity;

import jakarta.persistence.*;
import lombok.*;
import mongnam.spring.shoppingmall.constant.Role;
import mongnam.spring.shoppingmall.dto.MemberFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .role(Role.User)
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .name(memberFormDto.getName())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .build();


        return member;
    }
}
