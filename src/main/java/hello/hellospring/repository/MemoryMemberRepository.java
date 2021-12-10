package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    //메모리니까 저장을해야겠지 우선? 아래 선언.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;//0,1,2 이런식으로 키값 생성해줌.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
         return  member;
    }

    @Override
    public Optional<Member> findById(Long Id) {
        return Optional.ofNullable(store.get(Id));//null이 반환될수있으면 Optional로 감싸서 함.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//멤버들을 반환함.
    }

    //데이터 지우는용(테스트할때!)
    public void clearStore(){
        store.clear();
    }
}
