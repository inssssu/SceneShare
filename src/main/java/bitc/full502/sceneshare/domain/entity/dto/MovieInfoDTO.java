package bitc.full502.sceneshare.domain.entity.dto;

import lombok.Data;

@Data
public class MovieInfoDTO {

    private int movieId;
    private String movieTitle;
    private Integer movieRatingAvg;
    private String moviePosterUrl;
    private int bookmarkCnt;

}
