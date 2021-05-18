package hello.servlet.domain.member;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    private Long id; //id는 회원을 데이터베이스에 저장하면, 그게 아이디가 발급이 되게 할 거다.
    private String username;
    private int age;

    public Member() {
    }//기본 생성자

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
