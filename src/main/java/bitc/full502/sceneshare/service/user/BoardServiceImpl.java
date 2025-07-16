package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.repository.user.BoardDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDetailRepository boardDetailRepository;

    @Override
    public BoardEntity selectBoardDetail(int boardId) throws Exception {

        BoardEntity board = boardDetailRepository.findByBoardId(boardId);
        return board;
    }

    public void boardWrite(BoardEntity board) throws Exception {
        boardDetailRepository.save(board);
    }
}
