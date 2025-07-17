package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.user.UserAccountEntity;
import bitc.full502.sceneshare.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminUserManagementController {

    private final AdminUserService adminUserService;

    // 사용자 리스트
    @GetMapping("/userManagement")
    public ModelAndView adminUserManagement() throws Exception {

        ModelAndView mv = new ModelAndView("admin/userManagement");

        List<UserAccountEntity> userList = adminUserService.selectUserAccountList();
        mv.addObject("userList", userList);

        return mv;
    }

    // 사용자 상세
    @GetMapping("/userManagement/{userIdx}")
    public ModelAndView adminUserInfoDetail(@PathVariable int userIdx) throws Exception {

        ModelAndView mv = new ModelAndView("/admin/userInfoDetail");
        UserAccountEntity user = adminUserService.selectUserAccountDetail(userIdx);
        mv.addObject("user", user);

        return mv;
    }

    // 사용자 ID, 이름으로 검색
    @GetMapping("/userManagement/search")
    public Object adminSearchUser(@RequestParam("keyword") String keyword) throws Exception {
        // 공백제거
        String trimKeyword = keyword.trim();

        ModelAndView mv = new ModelAndView("/admin/userManagement");

        List<UserAccountEntity> userList = adminUserService.searchUserByKeyword(trimKeyword);
        mv.addObject("userList", userList);
        mv.addObject("keyword", keyword);

        return mv;
    }
}
