package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.admin.AdminEntity;
import bitc.full502.sceneshare.service.admin.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminLoginController {

    private final AdminService adminService;

    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLoginProcess(@RequestParam("adminName") String adminName,
                                    @RequestParam("adminPassword") String adminPassword,
                                    HttpServletRequest req) throws Exception {

        if (adminService.login(adminName, adminPassword)) {
            HttpSession session = req.getSession();

            session.setAttribute("adminName", adminName);
            return "redirect:/admin/dashboard";
        } else {
            System.out.println("adminLoginProcess 로그인 실패");
            return "/admin/login";
        }
    }
}
