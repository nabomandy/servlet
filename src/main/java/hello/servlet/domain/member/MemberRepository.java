package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*  동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap,AtomicLong 사용 고려
* */

public class MemberRepository {

    //static으로 했기 때문에 MemberRepository 가 new로 아무리 많아도 하나만 생성된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //아이디가 하나씩 증가하는 시퀀스로 쓸 거

    private static final MemberRepository instance = new MemberRepository(); //스프링을 안쓰고 있으므로 싱글톤으로 생성

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository(){
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //이렇게하면 스토어에 있는 모든 값들을 꺼내서 새로운 어레이리스트에 담아서 넘겨준다
        //이렇게하는 이유는, 밖에서 new ArrayList를 조작해도 store에 있는 벨류를 건들고 싶지 않아서이다.
    }

    public void clearStore() {
        store.clear();
    }

}
