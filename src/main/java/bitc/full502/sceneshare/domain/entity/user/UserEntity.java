package bitc.full502.sceneshare.domain.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(nullable = false, unique = true)
    private String username;

//    @Column(nullable = false)
//    private String password;

    @Column(nullable = false)
    private String name;

    private String status;

//    @Column(unique = true)
//    private String email;

//    private String gender;

//    private String userImg;

//    private LocalDate createDate;

    // role - user 추가 필요

//    @OneToMany(mappedBy = "user")
//    private List<BoardEntity> boards;

//    @OneToMany(mappedBy = "user")
//    private List<ReplyEntity> replies;

//    @OneToMany(mappedBy = "user")
//    private List<BookmarkEntity> bookmarks = new ArrayList<>();

}
