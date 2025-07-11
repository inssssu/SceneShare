package bitc.full502.sceneshare.controller.user;

import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.service.user.UserJoinService;
import bitc.full502.sceneshare.service.user.UserLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
public class UserLoginController {

    private final UserLoginService userLoginService;
    private final  UserJoinService userJoinService;

    @GetMapping("/user/login.do")
    public String login() {
        return "/user/login";
    }


    @RequestMapping("/user/loginProcess.do")
    public String loginProcess(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req) throws Exception {

        int result = userLoginService.isUserInfo(userId, userPw);

        if (result == 1) {
            UserEntity user = userLoginService.selectUserInfo(userId);

            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            session.setAttribute("userEmail", user.getUserEmail());

            return "redirect:/user/loginOK.do";
        }
        else {
            return "redirect:/user/loginFail.do";
        }
    }


    @RequestMapping("/user/loginOK.do")
    public ModelAndView loginOK(HttpServletRequest req) throws Exception {
        ModelAndView mv = new ModelAndView("/user/loginOK");

        HttpSession session = req.getSession();

        UserEntity user = new UserEntity();
        user.setUserId((String) session.getAttribute("userId"));
        user.setUserName((String) session.getAttribute("userName"));
        user.setUserEmail((String) session.getAttribute("userEmail"));
        mv.addObject("user", user);

        return mv;
    }

    @RequestMapping("/user/loginFail.do")
    public String loginFail() {
        return "/user/loginFail";
    }

    @RequestMapping("/user/logout.do")
    public String logout(HttpServletRequest req) throws Exception {

        HttpSession session = req.getSession();

        session.removeAttribute("userId");
        session.removeAttribute("userName");
        session.removeAttribute("userEmail");

        session.invalidate();

        return "/user/logout";
    }

    @GetMapping("/user/join.do")
    public String join() {
        return "/user/join";
    }

    @PostMapping("/user/create")
    public String createUser(UserEntity userIdx) throws Exception {
        userJoinService.newUser(userIdx);

        return "redirect:/user/login.do";
    }
}
