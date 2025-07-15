package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.domain.repository.user.MainBoardListRepository;
import bitc.full502.sceneshare.domain.repository.user.MainRepository;
import bitc.full502.sceneshare.domain.repository.user.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public List<MovieEntity> selectBoardListByBookmarkCnt() throws Exception {
        return mainRepository.findAllByOrderByBookmarkCntDesc();
    }

    @Override
    public List<MovieEntity> selectBoardListByReleaseDate() throws Exception {
        return mainRepository.findAllByOrderByReleaseDateDesc();
    }

    @Override
    public List<BoardEntity> selectBoardListByOrderByCreateDateDesc() throws Exception {
        return mainBoardListRepository.findAllByOrderByCreateDateDesc();
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
