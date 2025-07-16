package bitc.full502.sceneshare.domain.entity.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    @Column(nullable = false)
    private String movieTitle;

    @Column(nullable = false)
    private String movieGenre;

    @Column(nullable = false)
    private String movieDirector;

    @Column(nullable = false)
    private String movieDescription;

    @Column(nullable = false)
    private String posterUrl;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column
    private Integer bookmarkCnt;

    @Column
    private Integer rating;

    @OneToMany(mappedBy = "movie")
    private List<BookmarkEntity> bookmarks = new ArrayList<>();
}

