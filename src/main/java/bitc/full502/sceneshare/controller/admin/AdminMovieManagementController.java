package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.admin.MovieEntity;
import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;
import bitc.full502.sceneshare.service.admin.AdminMovieService;
import bitc.full502.sceneshare.service.admin.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminMovieManagementController {

    private final AdminMovieService adminMovieService;
    private final AdminNoticeService adminNoticeService;

    // 공지사항, 영화 정보 리스트
    @GetMapping("/movieManagement")
    public ModelAndView adminMovieManagement() throws Exception {

        ModelAndView mv = new ModelAndView("admin/movieManagement");
        List<NoticeEntity> noticeList = adminNoticeService.selectNoticeList();
        List<MovieEntity> movieList = adminMovieService.selectMovieList();
        mv.addObject("noticeList", noticeList);
        mv.addObject("movieList", movieList);

        return mv;
    }

    // 영화 정보 검색
//    @GetMapping("/movieManagement/search")
//    public Object adminMovieSearch(@RequestParam("keyword") String keyword) throws Exception {
//        String trimKeyword = keyword.trim();
//
//        ModelAndView mv = new ModelAndView("admin/movieManagement");
//
//        List<MovieEntity> movieList = adminMovieService.searchMovieByKeyword(trimKeyword);
//        mv.addObject("movieList", movieList);
//        mv.addObject("keyword", keyword);
//
//        return mv;
//    }

    // 공지사항, 영화 정보 검색 중 옵션에 따라 검색기능 필요 / 완료
    @GetMapping("/movieManagement/search")
    public ModelAndView adminMovieSearch(@RequestParam("keyword") String keyword, @RequestParam("option") String option) throws Exception {
        ModelAndView mv = new ModelAndView("admin/movieManagement");

        String trimKeyword = keyword.trim();
        option = option.toUpperCase();

        List<MovieEntity> movieList = new ArrayList<>();
        List<NoticeEntity> noticeList = new ArrayList<>();
        switch (option) {
            case "ALL":
                noticeList = adminNoticeService.searchByTitleOrContents(trimKeyword);
                movieList = adminMovieService.searchByTitleOrDescription(trimKeyword);
                break;

            case "NOTICE":
                noticeList = adminMovieService.searchByTypeAndTitleOrContents("NOTICE", trimKeyword);
                break;

            case "MOVIE":
                movieList = adminMovieService.searchByTypeAndTitleOrDescription("MOVIE", trimKeyword);
                break;

            default:
                break;
        }

        mv.addObject("noticeList", noticeList);
        mv.addObject("movieList", movieList);
        mv.addObject("option", option);
        mv.addObject("keyword", trimKeyword);

        return mv;
    }

    // 영화 상세 페이지
    @GetMapping("/movieManagement/movieDetail/{movieId}")
    public ModelAndView movieDetail(@PathVariable int movieId) throws Exception {

        ModelAndView mv = new ModelAndView("admin/movieDetail");

        MovieEntity movie = adminMovieService.selectMovieDetail(movieId);
        mv.addObject("movie", movie);

        return mv;
    }
}
