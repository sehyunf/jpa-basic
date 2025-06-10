package com.app.basic.domain.entity;

import com.app.basic.domain.type.ReplyStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class ReplyTest {
    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void insert() {
        Reply reply = new Reply();
        reply.setReplyWriter("둘리");
        reply.setReplyContent("게시글3");
        reply.setReplyStatus(ReplyStatus.PUBLIC);

//        영속
        entityManager.persist(reply);
    }

    @Test
    public void read() {
        Reply reply = entityManager.find(Reply.class, 1L);
        log.info("reply: {}", reply);
    }

    @Test
    public void update() {
        Reply reply = entityManager.find(Reply.class, 1L);
        reply.setReplyContent("RRRR");
    }

    @Test
    public void delete() {
        Reply reply = entityManager.find(Reply.class, 1L);
        entityManager.remove(reply);
    }

//    JPA QL
//    1) 와일드 카드 사용이 불가능하다!
//        테이블에 알리아스(소문자)를 붙여서 모든 데이터를 조회할 수 있다.
//    2) FROM절에서 가져오는 테이블은 DB의 테이블이 아니라 엔터티 객체이다.

    @Test
    public void replySelectAllTest() {
        log.info("JPA QL 데이터 조회 : {}", entityManager.createQuery("SELECT r FROM Reply r", Reply.class).getResultList().toString());

        log.info("단일행 조회 : {}", entityManager.createQuery("SELECT r FROM Reply r WHERE r.id = :id", Reply.class).setParameter("id", 2L).getSingleResult().toString());
    }


}