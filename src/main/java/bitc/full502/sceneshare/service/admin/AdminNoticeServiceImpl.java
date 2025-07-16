package bitc.full502.sceneshare.service.admin;

import bitc.full502.sceneshare.domain.entity.admin.NoticeEntity;
import bitc.full502.sceneshare.domain.repository.admin.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminNoticeServiceImpl implements AdminNoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public void saveNotice(NoticeEntity notice) {
        noticeRepository.save(notice);
    }

    @Override
    public NoticeEntity selectNoticeDetail(int noticeId) {
        return noticeRepository.findByNoticeId(noticeId);
    }

    @Override
    public List<NoticeEntity> selectNoticeList() {
        return noticeRepository.findAllByOrderByNoticeIdDesc();
    }

    @Override
    public List<NoticeEntity> searchByTitleOrContents(String keyword) {
        return noticeRepository.findByTitleContainingOrContentsContainingOrderByNoticeIdDesc(keyword, keyword);
    }
}
