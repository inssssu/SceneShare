package bitc.full502.sceneshare.domain.repository.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainBoardListRepository extends JpaRepository<BoardEntity,Integer> {

    List<BoardEntity> findAllByOrderByCreateDateDesc();
}
