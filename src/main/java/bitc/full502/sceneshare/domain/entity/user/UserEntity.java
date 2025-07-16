package bitc.full502.sceneshare.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String userName;

    @Column
    private String userEmail;

    @Column(nullable = false)
    private String gender;

    @Column
    private String userImg;

    @OneToMany(mappedBy = "user")
    private List<BookmarkEntity> bookmarks = new ArrayList<>();
}
