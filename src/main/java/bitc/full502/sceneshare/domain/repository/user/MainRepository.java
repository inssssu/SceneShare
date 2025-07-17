package bitc.full502.sceneshare.domain.repository.user;

import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepository extends JpaRepository<MovieEntity,Integer>{

    @Query("select bmk.movie.movieId, bmk.movie.movieTitle, count(bmk.bookmarkId), bmk.movie.ratingAvg, bmk.movie.posterUrl from BookmarkEntity as bmk group by bmk.movie.movieId order by count(bmk.bookmarkId) desc")
    List<Object[]> findAllByOrderByCountBookmarksDesc();
    List<MovieEntity> findAllByOrderByReleaseDateDesc();
}
