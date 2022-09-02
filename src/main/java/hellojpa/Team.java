package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @ManyToOne  // Member에 Team team에 걸려있다
    @JoinColumn(name = "MEMBER_ID")
    private Member member;



}
