package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.domain.repository.user.MainBoardListRepository;
import bitc.full502.sceneshare.domain.repository.user.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {

    private final MainRepository mainRepository;
    private final MainBoardListRepository mainBoardListRepository;

    @Override
    public List<MovieEntity> selectBoardListByHitCnt() throws Exception {
        return mainRepository.findAllByOrderByHitCntDesc();
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
    public List<MovieEntity> selectMovieSearchList(String searchMovie, String opt) {

        List<MovieEntity> MovieList = new ArrayList<>();

        switch (opt) {

            case "movieTitle":
                MovieList = mainRepository.findAllByMovieTitleContainsOrderByMovieTitleDesc(searchMovie);
                break;

            case "movieDirector":
                MovieList = mainRepository.findAllByMovieDirectorContainsOrderByMovieDirectorDesc(searchMovie);
                break;

            case "movieGenre":
                MovieList = mainRepository.findAllByMovieGenreContainsOrderByMovieGenreDesc(searchMovie);
                break;

        }

        return MovieList;
    }
}
