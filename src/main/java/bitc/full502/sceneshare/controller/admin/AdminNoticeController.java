package bitc.full502.sceneshare.controller.admin;

import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;
import bitc.full502.sceneshare.service.admin.AdminNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminNoticeController {

    private final AdminNoticeService adminNoticeService;

//    @GetMapping("/movieManagement")
//    public ModelAndView adminNoticeList() {
//
//        ModelAndView mv = new ModelAndView("admin/movieManagement");
//        List<NoticeEntity> notice = adminNoticeService.selectNoticeList();
//        mv.addObject("notice", notice);
//
//        return mv;
//    }

    @GetMapping("/noticeWrite")
    public String noticeWrite() {
        return "admin/noticeWrite";
    }

    @PostMapping("/noticeWrite")
    public String noticeWrite(NoticeEntity notice) {
        adminNoticeService.saveNotice(notice);

        return "redirect:/admin/movieManagement";
    }

    @GetMapping("/movieManagement/noticeDetail/{noticeId}")
    public ModelAndView noticeDetail(@PathVariable("noticeId") int noticeId) {

        ModelAndView mv = new ModelAndView("admin/noticeDetail");

        NoticeEntity notice = adminNoticeService.selectNoticeDetail(noticeId);
        mv.addObject("notice", notice);

        return mv;
    }

    @PutMapping("/noticeWrite/{noticeId}")
    public String noticeUpdate(@PathVariable("noticeId") int noticeId, NoticeEntity notice) {
        notice.setNoticeId(noticeId);
        adminNoticeService.saveNotice(notice);

        return "redirect:/admin/movieManagement";
    }
}
