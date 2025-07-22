package bitc.full502.sceneshare.domain.entity.user;


import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment_table")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;
    private String title;
    private String contents;
    private String genre;
//    private double rating;
//    private LocalDate createDate;
//    private LocalDate updateDate;
//    private int hitCnt;

    @ManyToOne
    @JoinColumn(name = "user_idx", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @OneToMany(mappedBy = "reply")
    private List<ReplyEntity> replies;
}
