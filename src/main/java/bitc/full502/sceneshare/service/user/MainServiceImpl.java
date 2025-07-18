package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.dto.MovieInfoDTO;
import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.domain.repository.user.MainBoardListRepository;
import bitc.full502.sceneshare.domain.repository.user.MainRepository;
import bitc.full502.sceneshare.domain.repository.user.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;
    private final MainBoardListRepository mainBoardListRepository;
    private final SearchRepository searchRepository;

    @Override
    public List<MovieInfoDTO> selectBoardListByBookmarkCnt() throws Exception {

        List<Object[]> selectmovie = mainRepository.findAllByOrderByCountBookmarksDesc();
        List<MovieInfoDTO> movieInfoList = new ArrayList<>();

        for (Object[] item : selectmovie) {
            MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
            movieInfoDTO.setMovieId((int)item[0]);
            movieInfoDTO.setMovieTitle((String)item[1]);
            movieInfoDTO.setBookmarkCnt((Long) item[2]);
            movieInfoDTO.setMovieRatingAvg((Integer) item[3]);
            movieInfoDTO.setMoviePosterUrl((String)item[4]);

            movieInfoList.add(movieInfoDTO);
        }

        return movieInfoList;
    }

    @Override
    public List<MovieInfoDTO> selectBoardListByReleaseDate() throws Exception {
        List<Object[]> selectmovie =  mainRepository.findAllByOrderByReleaseDateDesc();
        List<MovieInfoDTO> movieInfoList = new ArrayList<>();

        for (Object[] item : selectmovie) {
            MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
            movieInfoDTO.setMovieId((int)item[0]);
            movieInfoDTO.setMovieTitle((String)item[1]);
            movieInfoDTO.setBookmarkCnt((Long) item[2]);
            movieInfoDTO.setMovieRatingAvg((Integer) item[3]);
            movieInfoDTO.setMoviePosterUrl((String)item[4]);

            movieInfoList.add(movieInfoDTO);
        }

        return movieInfoList;
    }

    @Override
    public List<BoardEntity> selectBoardList() throws Exception {
        return mainBoardListRepository.findAll();
    }


    @Override
    public Map<String, List<MovieEntity>> movieSearchList(String searchMovie) throws Exception{

        List<MovieEntity> titleList = searchRepository.findAllByMovieTitleContaining(searchMovie);
        List<MovieEntity> directorList = searchRepository.findAllByMovieDirectorContaining(searchMovie);
        List<MovieEntity> genreList = searchRepository.findAllByMovieGenreContaining(searchMovie);

        Map<String, List<MovieEntity>> movieMap = new HashMap<>();
        movieMap.put("titleList", titleList);
        movieMap.put("directorList", directorList);
        movieMap.put("genreList", genreList);

        return movieMap;
    }
}
