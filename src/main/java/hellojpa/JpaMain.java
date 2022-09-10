package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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



            /*  // generatevalue 시퀀스에서 db에 계속 접근하지 않고 한번 가져올 때 50 개씩 가져오는 코드 test
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
            */




            /*
            //저장하는 코드

            Team team = new Team();
            team.setName("TEAMa");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
          //*  team.getMembers().add(member) 이 두개를 모두 써주는 걸 까먹을 수도 있다 그래서 연관관계 편의 메서드를 생성하면 편하다(Member로 이동)
            em.persist(member);

            team.addMember(member);

            //team.getMembers().add(member);  // 양방향 연관 관계에서 양쪽에 다 세팅해줘야 한다. test 케이스등

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());//1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("====================");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
                
            }
            System.out.println("====================");


             */


            /* 조인 테이블 , 싱글 테이블 , TABLE_PER_클래스, MappedSuperclass
            Member member = new Member();
            member.setUsername("user1");
            member.setCreatedBy("kim");
            member.setCreatedDate(LocalDateTime.now());


            Movie movie = new Movie();
            movie.setDirector("마동석");
            movie.setActor("우영우");
            movie.setName("이상한변호사");
            movie.setPrice(100000);


            Book book = new Book();
            book.setAuthor("베르나르베르베르");
            book.setIsbn("wds");
            book.setName("어린왕자");
            book.setPrice(1000);

            Album album = new Album();
            album.setArtist("송민호");
            album.setName("가시나");
            album.setPrice(10000);


            em.persist(member);
            em.persist(movie);
            em.persist(book);
            em.persist(album);

            em.flush();
            em.clear();

            Movie findMovie = em.find(Movie.class, movie.getId());
            Book findBook = em.find(Book.class, book.getId());
            Album findAlbum = em.find(Album.class, album.getId());
            System.out.println("findMovie = " + findMovie.getName());
            System.out.println("findBook = " + findBook.getName());
            System.out.println("album = " + album.getName());

             */


            /*

            Member member = new Member();
            member.setUsername("chanwoo");

            em.persist(member);

            em.flush();
            em.clear();

// 멤버만 찾기를 원하는데 team 쿼리도 jpa가 조인해서 보냈다
//            Member findMember = em.find(Member.class, member.getId());


            Member findMember = em.getReference(Member.class, member.getId());   //getrRegerence 사용법 프록시 기본
            System.out.println("findMember.Id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getUsername());
            System.out.println("findMember.name = " + findMember.getUsername());


             */


            /* lazy ,eager
            Team team = new Team();
            team.setName("레알마드리드");
            em.persist(team);

            Team team2 = new Team();
            team.setName("바르셀로나");
            em.persist(team2);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(team2);
            em.persist(member2);


            em.flush();
            em.clear();

            //Member m = em.find(Member.class, member1.getId());

            List<Member> members = em.createQuery("select m from Member m", Member.class)   // 만약에 지연로딩이 아닐 때 한번에 가져올때 jpql로 작성했을 때 쿼리가 2번나가는 현상
                    .getResultList();

//            System.out.println("m = " + m.getTeam().getClass());
//
//            System.out.println(" ============================ ");
//            m.getTeam().getName();   // team에 어떤 속성을 사용하는 시점에 프록시 객체가 초기화 되면서 db에서 값을 가져온다
//            System.out.println(" ============================ ");

             */


            /*
            //cascade = CascadeType.ALL,orphanRemoval = true 로 parent를 이용하여 child를 영속시키고 삭제할 수 있다  parent가 child의 생명 주기를 관리한다
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            em.persist(parent);
//            em.persist(child1);
//            em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);

             */

            /* 객체의 참조하면 변수 값이 완전히 바뀌어 버린다.
            Address address = new Address("city","street","10000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(address);
            em.persist(member2);

            //member1의 주소만 바꾸고 싶어서 했는데...,,  값타입의 실제 인스터스를 공휴하는 것은 위험하다
            member1.getHomeAddress().setCity("newCity");

             */


            /*
            //
            Address address = new Address("city","street","10000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address newAddress = new Address(("NewCity"),address.getStreet(),address.getZipcode());
            member1.setHomeAddress(newAddress);

             */

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity1","street", "10000"));

            member.getFavoritFoods().add("치킨");
            member.getFavoritFoods().add("베이컨");
            member.getFavoritFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1","street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2","street", "10000"));

             em.persist(member);

             em.flush();
             em.clear();

            System.out.println(" ============start================= ");
            Member findMember = em.find(Member.class, member.getId());

            /* 값타임 조회
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address= " + address.getCity());
            }

            Set<String> favoritFoods = findMember.getFavoritFoods();
            for (String favoritFood : favoritFoods) {
                System.out.println("favoritFood = " + favoritFood);
            }

             */

            //homeCity --> newCity 로 이사
            //findMember.getHomeAddress().setCity("newCity");  값 타입을 이런식으로 바꾸면 안된다

            //Address a = findMember.getHomeAddress();   // 인스턴스를 새로 만들어서 갈아 끼워야한다
            //findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));

            // 컬렉션 변경 치킨 --> 한식
           // findMember.getFavoritFoods().remove("치킨");
            //findMember.getFavoritFoods().add("한식");

            System.out.println(" ============start================= ");
            System.out.println("member = " + member);
            System.out.println("findMember = " + findMember);
            

            //값타입 컬렉션 주소 바꾸기 완전히 갈아 끼워야함
            //findMember.getAddressHistory().remove(new Address("old1","street", "10000"));  // 이퀄스랑 해쉬코드를 잘 구현 했다면 이퀄스 기반으로 찾아서 동작한다
            //findMember.getAddressHistory().add(new Address("newCIty1","street", "10000"));

            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close(); // 꼭 닫아야 한다
        }
        emf.close();
    }
}
