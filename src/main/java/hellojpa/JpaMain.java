package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain  {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try{
//            Member member = new Member();
//            member.setId(2L);                       // 회원등록
//            member.setName("HELLOB");

 //           Member findMember = em.find(Member.class, 1L);

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)  // 시작 지점
//                    .setMaxResults(8) // 끝 지점
//                    .getResultList();  // 대상이 테이블이 아닌 객체가 되야한다
//
//            for (Member member : result) {
//                System.out.println("member = " + member.getName());
//            }
//            tx.commit();

            /*
            // 비영속성 상태
            Member member = new Member();
            member.setName("helloJpa");
            member.setId(101L);

            //영속 상태   ..... 영속 상채라고 해도 바로 db에 쿼리가 날라가지 않는다.
            System.out.println("===before===");
            em.persist(member);
            System.out.println("===after===  ");

            Member findMember = em.find(Member.class, 101L);
            System.out.println("findMember = " + findMember.getName());
            System.out.println("findMember = " + findMember.getId());

            tx.commit();  //trangection을 commit하는 시점에 db에 쿼리를 날려준다
             */


            /* 영속성 엔티티의 동일성 보장 (1차 캐쉬로  )
            Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("result = " + (findMember2==findMember1));
            */


            // 엔티티 등록 트랜잭션을 지원하는 쓰기 지연
            /*
            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"B");

            em.persist(member1);
            em.persist(member2);
            System.out.println("=====================");

             */



            /* 변경 감지(dirty checking)
            Member member = em.find(Member.class, 150L);
            member.setName("zzzzz"); // 컬렉션 처럼 사용하기 떄문에 persist가 필요없다


            System.out.println(" ====================" );
             */

    
            /* flush
            Member member = new Member(200L,"member200");
            em.persist(member);

            em.flush();
            System.out.println(" ==============" );
            
             */


            /*
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAAAA");

            em.detach(member);
            //em.clear(); 통으로 초기화 한다.

            System.out.println(" ==============" );

             */

            Member member1 = new Member();
            member1.setUsername("A");

            Member member2 = new Member();
            member2.setUsername("B");

            Member member3 = new Member();
            member3.setUsername("C");

            System.out.println(" ===================== ");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("member2.getId() = " + member2.getId());
            System.out.println("member3.getId() = " + member3.getId());

            System.out.println(" ===================== ");

            tx.commit();

        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); // 꼭 닫아야 한다
        }
        emf.close();
    }
}
