package hellojpa;
import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR", sequenceName = "MEMBER_SEQ",     //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 50)
@Entity
public class Member {


    //연관 관계 맵핑 !!! 매우 중요
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private  String username;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;
    @ElementCollection  // 값타입 컬렉션은 적어줘야 한다
    @CollectionTable(name = "FAVORITE_FOODS" ,joinColumns =
    @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") //  얘외 적으로 STRING은 값이 하나고 내가 정의한 것이 아니기 때문에
    private Set<String> favoritFoods =new HashSet<>();


//    @ElementCollection  값 타입으로 매핑
//    @CollectionTable(name = "ADDRESS ",joinColumns = @JoinColumn(name = "MEMBER_ID"))
//    private List<Address> addressHistory = new ArrayList<>();

    // 엔티티로 맵핑
    @OneToMany(cascade = CascadeType.ALL ,orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistory = new ArrayList<>();


    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "city",column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street",column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipcode",column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;

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

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoritFoods() {
        return favoritFoods;
    }

    public void setFavoritFoods(Set<String> favoritFoods) {
        this.favoritFoods = favoritFoods;
    }

    public List<AddressEntity> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressEntity> addressHistory) {
        this.addressHistory = addressHistory;
    }

    public Address getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }

    /* //
    @ManyToOne(fetch = FetchType.LAZY)  //team을 프록시 객체로 조회한다 // member클래스만 db에서 조회한다  //지연로딩과 즉시 로딩
    @JoinColumn(name = "ITEM_ID")
    private Team team;
     */


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
