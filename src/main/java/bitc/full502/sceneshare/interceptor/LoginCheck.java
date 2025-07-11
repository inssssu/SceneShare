package bitc.full502.sceneshare.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {

        HttpSession session = req.getSession();

        String userId = (String) session.getAttribute("userId");

        if (userId == null || userId.isEmpty()) {

            resp.sendRedirect("admin/login");

            return false;
        } else {
            session.setMaxInactiveInterval(60 * 60);
            return true;
        }
    }
}
