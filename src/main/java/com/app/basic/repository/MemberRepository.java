package com.app.basic.repository;

import com.app.basic.domain.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

//    데이터 추가
    public void save(Member member) {
        entityManager.persist(member);
    }

//    1개 유저 조회
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

//    전체 유저 조회
    public List<Member> findAll() {
        return entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }

//    update - 변경감지(더티 체크)

//    삭제
    public void delete(Member member) {
        entityManager.remove(member);
    }

}
