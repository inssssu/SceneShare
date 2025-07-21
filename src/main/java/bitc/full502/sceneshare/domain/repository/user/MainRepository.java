package bitc.full502.sceneshare.domain.repository.user;

import bitc.full502.sceneshare.domain.entity.dto.MovieInfoDTO;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.domain.repository.user.projection.MovieBookmarkProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MainRepository extends JpaRepository<MovieEntity, Integer> {

    @Query("""
        select m.movieId      as movieId,
               m.movieTitle   as movieTitle,
               count(b.bookmarkId) as bookmarkCnt,
               m.ratingAvg    as ratingAvg,
               m.posterUrl    as posterUrl
        from   MovieEntity m
        left join BookmarkEntity b on b.movie = m
        group by m.movieId, m.movieTitle, m.ratingAvg, m.posterUrl
        order by count(b.bookmarkId) desc
    """)
    List<MovieInfoDTO> findAllByBookmarkCntDesc();

    @Query("""
        select m.movieId,
               m.movieTitle,
               count(b.bookmarkId),
               m.ratingAvg,
               m.posterUrl
        from   MovieEntity m
        left join BookmarkEntity b on b.movie = m
        group by m.movieId, m.movieTitle, m.ratingAvg, m.posterUrl, m.releaseDate
        order by m.releaseDate desc
    """)
    List<MovieInfoDTO> findAllByReleaseDateDesc();
}


