package bitc.full502.sceneshare.service.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.repository.user.BoardDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDetailRepository boardDetailRepository;

    @Override
    public BoardEntity selectBoardDetail(int boardId) throws Exception {

        BoardEntity board = boardDetailRepository.findByBoardId(boardId);
        return board;
    }

    public void boardWrite(BoardEntity board, int movieId) throws Exception {
        boardDetailRepository.save(board);


   //     평점 입력한 사람 각각의 점수
//        총점 연산
        // 총 평점 입력 인원 수
//        평균 점수 연산
//        받아온 영화번호로 지정한 영화 검색
//        MovieEntity에 평균 점수 저장
    }



    @Override
    public Object[] boardCnt() throws Exception{
        Object[] board = boardDetailRepository.countByBoardId();
        return board;
    }
}























