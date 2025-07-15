package bitc.full502.sceneshare.domain.entity.admin;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "movie_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private String title;
    private String genre;
    private String director;
    private String description;

    // ERD 의 movie, notice 에 option 컬럼 추가 필요
    private String type;
//    private LocalDate releaseDate;
//    private String posterUrl;
}
