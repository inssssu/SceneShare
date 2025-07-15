package bitc.full502.sceneshare.controller.user;

import bitc.full502.sceneshare.domain.entity.user.MovieEntity;
import bitc.full502.sceneshare.service.user.MovieDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class UserMovieDetailController {

    private final MovieDetailService movieDetailService;

    @GetMapping("/movieDetail/{movieId}")
        public ModelAndView movieDetail(@PathVariable("movieId") int movieId) throws Exception {
        ModelAndView mv = new ModelAndView("/user/movieDetail");

        MovieEntity movie = movieDetailService.selectMovieDetail(movieId);
        mv.addObject("movie", movie);

        return mv;
    }
}
