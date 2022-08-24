package association;

import association.domain.Mate;
import association.domain.Team;
import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AssociationApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("association");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Mate mate = new Mate();
            mate.setUserName("member1");

            mate.setTeam(team);
            em.persist(mate);

            /* DB에서 직접 값을 가져오고 싶은 경우
             * em.flush();
             * em.clear();
             */

            Mate findMate = em.find(Mate.class, mate.getId());
            Team findTeam = findMate.getTeam();
            System.out.println("findTeam = " + findTeam.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
