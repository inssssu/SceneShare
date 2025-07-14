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
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserMainController {

    private final MainService mainService;

    @GetMapping("/main")
    public ModelAndView main() throws Exception {
        ModelAndView mv = new ModelAndView("/user/main");

        List<MovieEntity> movieListHitCnt = mainService.selectBoardListByHitCnt();
        mv.addObject("movieListHitCnt", movieListHitCnt);

        List<MovieEntity> movieListReleaseDate = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieListReleaseDate", movieListReleaseDate);

        List<BoardEntity> boardList = mainService.selectBoardListByOrderByCreateDateDesc();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @GetMapping("/main/movieListHitCnt")
    public ModelAndView movieListHitCnt() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieListHitCnt");

        List<MovieEntity> movieListHitCnt = mainService.selectBoardListByHitCnt();
        mv.addObject("movieListHitCnt", movieListHitCnt);

        return mv;
    }

    @GetMapping("/main/movieListReleaseDate")
    public ModelAndView movieList2() throws Exception {
        ModelAndView mv = new ModelAndView("movieListReleaseDate");

        List<MovieEntity> movieListReleaseDate = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieList2", movieListReleaseDate);

        return mv;
    }

    @GetMapping("/main/boardList")
    public ModelAndView boardList() throws Exception {
        ModelAndView mv = new ModelAndView("/user/boardList");

        List<BoardEntity> boardList = mainService.selectBoardListByOrderByCreateDateDesc();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @GetMapping("/main/search")
    public ModelAndView movieSearchResult(@RequestParam("searchMovie") String searchMovie) throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieSearchResult");

//        List<List<MovieEntity>> movieList = mainService.movieSearchList(searchMovie);
//        mv.addObject("movieList", movieList);

        Map<String, List<MovieEntity>> movie = mainService.movieSearchList(searchMovie);
        mv.addObject("movie", movie);
        mv.addObject("searchMovie", searchMovie);

        return mv;
    }

    @ResponseBody
    @GetMapping("/main/searchResult")
    public ModelAndView searchMovie(@RequestParam("searchMovie") String searchMovie) throws Exception {

        ModelAndView mv = new ModelAndView("/user/movieSearchResult");

        Map<String, List<MovieEntity>> movieResultList = mainService.movieSearchList(searchMovie);
        mv.addObject("movieResultList", movieResultList);

        return mv;
    }


}
