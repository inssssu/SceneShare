package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;

public interface BoardService {

    BoardEntity selectBoardDetail(int boardId) throws Exception;

    void boardWrite(BoardEntity board, int movieId) throws Exception;
}
