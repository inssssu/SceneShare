package bitc.full502.sceneshare.domain.repository.user;

import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainRepository extends JpaRepository<MovieEntity,Integer>{

    List<MovieEntity> findAllByOrderByBookmarkCntDesc();
    List<MovieEntity> findAllByOrderByReleaseDateDesc();
}
