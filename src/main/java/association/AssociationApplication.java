package association;

import association.domain.Mate;
import association.domain.Team;
import entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class AssociationApplication {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("association");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try{
            tx.begin();
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Mate mate = new Mate();
            mate.setUserName("member1");
            // 정석적인 방법으로 객체에 연관관계를 직접 넣어준다. -> 보통 연관관계 편의 메서드를 정의한다.
            mate.setTeam(team);
            team.getMates().add(mate);
            em.persist(mate);

            /* insert 쿼리가 발행되지 않았기 때문에, select 쿼리 또한 발행되지 않는다.
            *  따라서 flush를 실행하여 insert가 완료된 후, select 쿼리를 보낼 수 있도록 한다.
            * */
            em.flush();

            Mate findMate = em.find(Mate.class, mate.getId());
            Team findTeam = findMate.getTeam();
            List<Mate> findMates = findTeam.getMates();
            System.out.println("team = " + findTeam.getName());
            findMates.forEach((m)-> System.out.println("member = " + m.getUserName()));

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
