package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.user.CommentEntity;
import bitc.full502.sceneshare.domain.entity.user.UserEntity;
import bitc.full502.sceneshare.service.admin.AdminCommentService;
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
public class AdminCommentController {

    private final AdminCommentService adminCommentService;
    private final AdminUserService adminUserService;

    @GetMapping("/comment")
    public ModelAndView commentList() {

        ModelAndView mv = new ModelAndView("admin/commentManagement");
        List<UserEntity> userList = adminUserService.selectUserAccountList();
        mv.addObject("userList", userList);

        return mv;
    }

    @GetMapping("/commentDetail/{userIdx}")
    public ModelAndView commentDetail(@PathVariable("userIdx") int userIdx) {

        ModelAndView mv = new ModelAndView("admin/commentDetail");

        UserEntity user = adminUserService.selectUserAccountDetail(userIdx);
        CommentEntity comments = adminCommentService.selectCommentDetail(userIdx);

        mv.addObject("user", user);
        mv.addObject("comments", comments);

        return mv;
    }

    @GetMapping("/comment/search")
    public ModelAndView adminSearchComment(@RequestParam("keyword") String keyword) throws Exception {

        String trimKeyword = keyword.trim();

        ModelAndView mv = new ModelAndView("admin/commentManagement");

        List<UserEntity> userList = adminUserService.selectUserAccountList();
        mv.addObject("userList", userList);
        mv.addObject("keyword", keyword);

        return mv;
    }
}
