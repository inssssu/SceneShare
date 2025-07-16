package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;
import bitc.full502.sceneshare.domain.repository.admin.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminMovieServiceImpl implements AdminMovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieEntity> selectMovieList() {
        return movieRepository.findAllByOrderByMovieIdDesc();
    }

//    공지사항 & 영화리스트
    @Override
    public List<MovieEntity> searchByTitleOrDescription(String keyword) {
        return movieRepository.findByTitleContainingOrDescriptionContainingOrderByMovieIdDesc(keyword, keyword);
    }

//    영화 리스트
    @Override
    public List<MovieEntity> searchByTypeAndTitleOrDescription(String type, String keyword) {
        return movieRepository.findByTypeAndTitleOrDescription(type, keyword);
    }

//    공지사항
    @Override
    public List<NoticeEntity> searchByTypeAndTitleOrContents(String type, String keyword) {
        return movieRepository.findByTypeAndTitleOrContents(type, keyword);
    }

    @Override
    public MovieEntity selectMovieDetail(int movieId) {
        return movieRepository.findByMovieId(movieId);
    }

}
