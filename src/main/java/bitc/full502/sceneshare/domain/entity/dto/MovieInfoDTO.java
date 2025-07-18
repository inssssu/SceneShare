package bitc.full502.sceneshare.domain.entity.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MovieInfoDTO {

    @Column(nullable = false)
    private int movieId;
    private String movieTitle;
    private Integer movieRatingAvg;
    private String moviePosterUrl;
    private Long bookmarkCnt;
}
