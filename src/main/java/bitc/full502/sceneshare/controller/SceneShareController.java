package bitc.full502.sceneshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SceneShareController {

    @GetMapping("/main")
    public String main() {
        return "user/main";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login/login";
    }

    @GetMapping("/join")
    public String join() {
        return "user/login/join";
    }

    @GetMapping("/myPage")
    public String myPage() {
        return "user/login/myPage";
    }

    @GetMapping("/myUpdate")
    public String myUpdate() {
        return "user/login/myUpdate";
    }

    @GetMapping("/searchList")
    public String searchList() {
        return "user/sub/searchList";
    }

    @GetMapping("/movieList1")
    public String movieList1() {
        return "user/sub/movieList1";
    }

    @GetMapping("/movieDetail")
    public String movieDetail() {
        return "user/sub/movieDetail";
    }

    @GetMapping("/reviewList")
    public String reviewList() {
        return "user/board/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail() {
        return "user/board/reviewDetail";
    }

    @GetMapping("/noticeDetail")
    public String noticeDetail() {
        return "user/board/noticeDetail";
    }


}
