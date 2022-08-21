package hello;

import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        int type = 1;
        // get database transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // Insert query
            if (type == 0) {
                Member member = new Member();
                member.setId(2L);
                member.setName("UNHEE");
                em.persist(member);
            // Update
            } else if (type == 1) {
                Member findMember = em.find(Member.class, 1L);
                findMember.setName("RENAME_UNHEE2");

                // 따로 Persist를 호출하지 않아도, 변경된다.
                // Transaction commit 시점에 자동으로 update query를 발행한다.
                //em.persist(findMember);
            } else if (type == 2) {
                Member findMember = em.find(Member.class, 2L);
                em.remove(findMember);
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
