package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;

import java.util.List;

public interface AdminMovieService {

    List<MovieEntity> selectMovieList();

    List<MovieEntity> searchByTitleOrDescription(String keyword);

    List<MovieEntity> searchByTypeAndTitleOrDescription(String type, String keyword);

    List<NoticeEntity> searchByTypeAndTitleOrContents(String type, String keyword);

    MovieEntity selectMovieDetail(int movieId);
}
