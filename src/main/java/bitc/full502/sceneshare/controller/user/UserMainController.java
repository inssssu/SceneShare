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

        List<MovieEntity> movieListBookmarkCnt = mainService.selectBoardListByBookmarkCnt();
        mv.addObject("movieListBookmarkCnt", movieListBookmarkCnt);

        List<MovieEntity> movieListReleaseDate = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieListReleaseDate", movieListReleaseDate);

        List<BoardEntity> boardList = mainService.selectBoardListByOrderByCreateDateDesc();
        mv.addObject("boardList", boardList);

        return mv;
    }

    @GetMapping("/main/movieListBookmarkCnt")
    public ModelAndView movieListBookmarkCnt() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieListBookmarkCnt");

        List<MovieEntity> movieListBookmarkCnt = mainService.selectBoardListByBookmarkCnt();
        mv.addObject("movieListBookmarkCnt", movieListBookmarkCnt);

        return mv;
    }

    @GetMapping("/main/movieListReleaseDate")
    public ModelAndView movieListReleaseDate() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieListReleaseDate");

        List<MovieEntity> movieListReleaseDate = mainService.selectBoardListByReleaseDate();
        mv.addObject("movieListReleaseDate", movieListReleaseDate);

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

    @GetMapping("/main/movieDetail")
    public ModelAndView movieDetail() throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieDetail");

        return mv;
    }
}
