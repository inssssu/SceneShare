package bitc.full502.sceneshare.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.PrintWriter;

public class LoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse resp,
                             Object handler) throws Exception {

        HttpSession session = req.getSession(false);
        String userId = (session == null) ? null : (String) session.getAttribute("userId");

        if (userId == null || userId.isEmpty()) {

            //            접속 방식이 ajax 통신인지 확인
            boolean isAjax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));

            if (isAjax) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.setContentType("application/json;charset=UTF-8");
                try (PrintWriter w = resp.getWriter()) {
                    w.write("{\"msg\":\"로그인이 필요한 서비스입니다.\"}");
                }
            } else {
                String loginUrl = req.getContextPath() + "/user/login.do";
                resp.setContentType("text/html;charset=UTF-8");
                try (PrintWriter w = resp.getWriter()) {
                    w.write("<script>alert('로그인이 필요한 서비스입니다.');"
                            + "location.href='" + loginUrl + "';</script>");
                }
            }
            return false;
        }

        return true;
    }
}
