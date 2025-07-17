package bitc.full502.sceneshare.domain.repository.admin;

import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    List<MovieEntity> findAllByOrderByMovieIdDesc();

    // 내림차순으로 뿌려주고 싶다면 order by 를 모두 붙여줘야 하는건지? 너무 길어져도 상관없나?
    List<MovieEntity> findByTitleContainingOrDirectorContaining(String title, String director);

    List<MovieEntity> findByTitleContainingOrDescriptionContainingOrderByMovieIdDesc(String title, String description);

    @Query("SELECT m FROM MovieEntity m WHERE m.type = :type AND (m.title LIKE %:keyword% OR m.description LIKE %:keyword%) ORDER BY m.movieId DESC")
    List<MovieEntity> findByTypeAndTitleOrDescription(@Param("type") String type, @Param("keyword") String keyword);
}
