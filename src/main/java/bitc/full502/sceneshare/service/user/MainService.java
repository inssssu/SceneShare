package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;

import java.util.List;
import java.util.Map;

public interface MainService {

    List<MovieEntity> selectBoardListByHitCnt() throws Exception;

    List<MovieEntity> selectBoardListByReleaseDate() throws Exception;

    List<BoardEntity> selectBoardListByOrderByCreateDateDesc() throws Exception;

    Map<String, List<MovieEntity>> movieSearchList(String searchMovie) throws Exception;
}
