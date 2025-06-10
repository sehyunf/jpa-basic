package com.app.basic.domain.entity;

import com.app.basic.domain.type.MemberGender;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class MemberTest {

    @Test
    public void ordinalTest() {
        MemberGender m = MemberGender.MALE;
        log.info("Male : {}", m.toString());
        log.info("Male : {}", m);
    }

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void memberInsertTest() {

        Member member = new Member();
        member.setMemberEmail("test123@gmail.com");
        member.setMemberPassword("1234");
        member.setMemberName("홍길동");
        member.setMemberAge(25);
        member.setMemberGender(MemberGender.MALE);

        entityManager.persist(member);

//        ctrl + alt + v (리턴값 바로 달기)
        Member foundMember = entityManager.find(Member.class, member.getId());

        log.info("result: {}", member==foundMember);

    }

//    마지막 유저를 조회 후 로그에 출력하기
    @Test
    public void selectLastMemberTest() {

        Member member = entityManager.createQuery("select m from Member m order by m.id desc fetch next 1 row only", Member.class).getSingleResult();
        member.setMemberAge(20);
        log.info("result: {}", member);
    }

    @Test
    public void memberDeleteTest() {
        Member foundmember = entityManager.find(Member.class, 102);
        entityManager.remove(foundmember);
        log.info("result: {}", foundmember.toString());
    }

    @Test
    public void memberMergeTest() {
//        영속 상태 -> 준영속 상태
//        entityManager.flush(); entityManager.detach();
//        준영속 상태 -> 영영속 상태
//        entityManager.merge();

//        (영속)
        Member foundmember = entityManager.find(Member.class, 2L);
//        (준영속)
        entityManager.detach(foundmember);
        foundmember.setMemberName("고길동");
//        (영속)
        entityManager.merge(foundmember);
        foundmember.setMemberName("도우너");

    }

}