package bitc.full502.sceneshare.controller.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.service.user.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserBoardController {

    private final BoardService boardService;

    @GetMapping("/user/boardDetail")
    public ModelAndView boardDetail(@RequestParam("boardId") int boardId) throws Exception {
        ModelAndView mv = new ModelAndView("/user/boardDetail");

        BoardEntity board = boardService.selectBoardDetail(boardId);
        mv.addObject("board", board);

        return mv;
    }
}
