package bitc.full502.sceneshare.controller.user;

import bitc.full502.sceneshare.domain.entity.user.BoardEntity;
import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.service.user.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserMainController {

    private final MainService mainService;

    @GetMapping("/main")
    public ModelAndView main() throws Exception {
        ModelAndView mv = new ModelAndView("/user/main");

        List<MovieEntity> movieList1 = mainService.selectBoardListByHitCnt();
        mv.addObject("movieList1", movieList1);

        List<MovieEntity> movieList2 = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieList2", movieList2);

        List<BoardEntity> boardList = mainService.selectBoardListByOrderByCreateDateDesc();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @GetMapping("/main/List1")
    public ModelAndView movieList1() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieList1");

        List<MovieEntity> movieList1 = mainService.selectBoardListByHitCnt();
        mv.addObject("movieList1", movieList1);

        return mv;
    }

    @GetMapping("/main/List2")
    public ModelAndView movieList2() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieList2");

        List<MovieEntity> movieList2 = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieList2", movieList2);

        return mv;
    }

    @GetMapping("/main/boardList")
    public ModelAndView boardList() throws Exception {
        ModelAndView mv = new ModelAndView("/user/boardList");

        List<BoardEntity> boardList = mainService.selectBoardListByOrderByCreateDateDesc();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @ResponseBody
    @GetMapping("/main/search")
    public Object MovieSearch(@RequestParam("searchMovie") String searchMovie, @RequestParam("option") String opt) {

        List<MovieEntity> MovieList = mainService.selectMovieSearchList(searchMovie, opt);

        return MovieList;
    }
}
