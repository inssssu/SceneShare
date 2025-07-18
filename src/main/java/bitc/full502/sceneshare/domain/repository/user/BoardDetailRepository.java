package bitc.full502.sceneshare.domain.repository.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardDetailRepository extends JpaRepository<BoardEntity,Integer> {

    BoardEntity findByBoardId(int boardId) throws Exception;

}
