package bitc.full502.sceneshare;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class LoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");
        System.out.println("사용자 ID : " + userId);

        if (userId == null || userId.isEmpty()) {
            System.out.println("비 로그인 상태");
            System.out.println((String) session.getAttribute("userId"));

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();

            String script = "<script>";
            script += "alert('로그인이 필요한 서비스 입니다.');";
            script += "location.href='/user/login.do';";
            script += "</script>";

            writer.print(script);

            return false;
        }
        else {
            System.out.println("로그인 상태");
            return true;
        }

    }
}
