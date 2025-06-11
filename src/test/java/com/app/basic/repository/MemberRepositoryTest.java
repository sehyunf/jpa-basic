package com.app.basic.repository;

import com.app.basic.domain.entity.Member;
import com.app.basic.domain.type.MemberGender;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
@Transactional
@Commit
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void memberSaveTest() {
        Member member = new Member();
        member.setMemberEmail("me@gmail.com");
        member.setMemberPassword("123456");
        member.setMemberName("박세현");
        member.setMemberGender(MemberGender.MALE);

        memberRepository.save(member);
    }

    @Test
    public void memberFindTest() {
        Optional<Member> member = memberRepository.findById(952L);
        log.info("member: {}", member.orElseThrow(RuntimeException::new));
    }

    @Test
    public void memberUpdateTest() {
//        영속
        Optional<Member> foundMember = memberRepository.findById(952L);
        foundMember.ifPresent(member -> {
            member.setMemberAge(20);
        });
    }

//    전체 데이터 조회
    @Test
    public void memberFindAllTest() {
        Iterable<Member> members = memberRepository.findAll();
        log.info("members: {}", members);
    }


    @Test
    public void memberDeleteTest() {
        Optional<Member> foundMember = memberRepository.findById(952L);
        foundMember.ifPresent(member -> {
            memberRepository.delete(member);
        });
    }

}