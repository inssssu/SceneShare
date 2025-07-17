package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
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

    @Override
    public List<MovieEntity> searchByTitleOrDescription(String keyword) {
        return movieRepository.findByTitleContainingOrDescriptionContainingOrderByMovieIdDesc(keyword, keyword);
    }

    @Override
    public List<MovieEntity> searchByTypeAndTitleOrDescription(String type, String keyword) {
        return movieRepository.findByTypeAndTitleOrDescription(type, keyword);
    }

}
