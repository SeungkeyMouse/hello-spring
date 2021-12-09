package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //다같이 돌리면 에러 나는 이유 : findAll먼저 실행됐음.->테스트 하나 끝나면 데이터 클리어해야됨.
    //"★테스트는 순서에 상관없이 의존도 없이 설계되어야함★"
    @AfterEach
    public void afterEach(){
        repository.clearStore();//하나의 테스트 끝날때마다 저장소를 지움.
    }

    //Test해보기 junit으로 하는 메서드
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result==member)); //글자로 계속 보는 테스트 result = true
        //assertEquals(result, member);//BUILD SUCCESSFUL in 1s
        assertThat(member).isEqualTo(result);//요즘 많이쓰는것
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();//shift + f6하면 한꺼번에 이름 바뀜
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();//shift + f6하면 한꺼번에 이름 바뀜
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}
