package persistence;

import entity.Member;

import javax.persistence.*;
import java.util.List;

public class PersistenceContextExample {
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");


    public static void main(String[] args) {

        //persistContext();
        //searchCache();
        //lazyWrite();
        //detectChanges();
        //testFlush();
        detachFromContext();
        emf.close();
    }

    private static void persistContext() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            // 객체를 생성한 상태 (비영속)
            Member member = new Member(5L, "회원1");

            // 객체를 저장한 상태 (영속)
            System.out.println("=== BEFORE ===");
            // 1차 캐시에 등록
            em.persist(member);            // 준영속 : em.detach, 삭제 : em.remove

            // 조회시, 1차 캐시부터 조회한다.
            em.find(Member.class, 5L);
            System.out.println("=== AFTER ===");

            // commit 시점에서 쿼리 발행
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void searchCache() {
        System.out.println("=========== Search Cache Start ===========");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            System.out.println("=== BEFORE ===");
            // 쿼리가 한번만 발행된다.
            Member findMember1 = em.find(Member.class, 5L);
            Member findMember2 = em.find(Member.class, 5L);

            // 영속 엔티티의 동일성을 보장한다.
            System.out.println("result = " + (findMember1 == findMember2));
            System.out.println("=== AFTER ===");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void lazyWrite() {
        System.out.println("=========== lazy write Start ===========");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member(6L, "TEST1");
            Member member2 = new Member(7L, "TEST2");

            System.out.println("=== BEFORE ===");
            // INSERT 쿼리를 영속 컨텍스트에서 관리하는 쓰기 지연 SQL 저장소에 쌓아둔다.
            // 해당 엔티티의 내용을 1차 캐시에 저장한다.
            em.persist(member1);
            em.persist(member2);
            System.out.println("=== AFTER ===");

            // commit 시점에 쿼리를 발행한다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void detectChanges() {
        System.out.println("=========== detect changes Start ===========");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 6L);
            // 엔티티의 값의 변경을 감지한다.
            member.setName("CHANGED"); // == em.delete(member);
            System.out.println("=== AFTER ===");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void testFlush() {
        System.out.println("=========== test Flush Start ===========");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Member member = new Member(200L, "member200");
            em.persist(member);
            // flush 호출 시, 쿼리가 발행된다.
            em.flush();
            // JPQL 쿼리 실행 시 플러시가 자동으로 호출된다.
            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            List<Member> members = query.getResultList();
            System.out.println("=== AFTER ===");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }

    private static void detachFromContext() {
        System.out.println("=========== detach Start ===========");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 200L);
            member.setName("DETACHED");

            // 준영속 상태로 변경한다.
            em.detach(member);
            System.out.println("=== AFTER ===");

            // Update 쿼리가 발행되지 않는다.
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
