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
            mate.changeTeam(team); // 하지 않으면, 외래키가 NULL이 된다.. -> 연관관계 편의 메서드
            // team.addMate(mate) -> 반대쪽에 연관관계 편의 메서드를 구현해도 된다. 상황을 보고 결정하자.
            em.persist(mate);

            /* 연관관계 편의 매서드
            team.getMates().add(mate);
            */

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
