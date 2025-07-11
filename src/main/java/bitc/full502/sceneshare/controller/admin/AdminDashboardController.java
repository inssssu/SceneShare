package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;
import bitc.full502.sceneshare.service.admin.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminDashboardController {

    private final UserService userService;

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/userManagement")
    public ModelAndView adminUserManagement() throws Exception {

        ModelAndView mv = new ModelAndView("admin/userManagement");

        List<UserAccountEntity> userList = userService.selectUserAccountList();
        mv.addObject("userList", userList);

        return mv;
    }

}
