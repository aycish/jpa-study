package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class NewMemberTest {

    public static void main(String[] args) {
       EntityManagerFactory emf = Persistence.createEntityManagerFactory("new-hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            NewMember newMember = new NewMember();
            // @Id 어노테이션을 사용한 상태, 직접 ID를 할당하는 방법
            //newMember.setId(1L);
            newMember.setUsername("E");

            // DB에 순서가 저장된다.
            newMember.setRoleType(RoleType.USER);

            em.persist(newMember);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
