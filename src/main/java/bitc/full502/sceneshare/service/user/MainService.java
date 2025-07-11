package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;

import java.util.List;

public interface MainService {

    List<MovieEntity> selectBoardListByHitCnt() throws Exception;

    List<MovieEntity> selectBoardListByReleaseDate() throws Exception;

    List<BoardEntity> selectBoardListByOrderByCreateDateDesc() throws Exception;

    List<MovieEntity> selectMovieSearchList(String searchMovie, String opt);
}
