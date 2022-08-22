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
            newMember.setId(1L);
            newMember.setUsername("A");

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
