package hellojpa;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ",     //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)
@Entity
public class Member extends BaseEntity {


    //연관 관계 맵핑 !!! 매우 중요
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private  String username;

//    @Column(name = "TEAM_ID")  //객체지향 관점에서 별로다
//    private Long teamId;

    @ManyToOne(fetch = FetchType.LAZY)  //team을 프록시 객체로 조회한다 // member클래스만 db에서 조회한다
    @JoinColumn(name = "ITEM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//            generator = "MEMBER_SEQ_GENERATOR")
//    private Long id;
//
//    @Column(name = "name",nullable = false)
//    private String username;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Member() {
//    }





    /*
    @Id
    private Long id;

    @Column(name = "name",nullable = false)  //객체에서는 userName을 사용하고 싶지만 DB에서는 name을 사용하고 싶을 때
    private String username;

    private int age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

//    private LocalDate testLocalDate;
//    private LocalDateTime testLocalDateTime;   // 요즘에는 자바에서 제공해준다

    @Lob
    private String description;

    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //Getter, Setter…

     */
